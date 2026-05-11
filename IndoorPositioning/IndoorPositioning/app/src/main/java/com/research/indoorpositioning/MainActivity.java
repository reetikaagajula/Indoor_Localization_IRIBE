package com.research.indoorpositioning;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResult;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private WifiManager    wifiManager;
    private WifiRttManager rttManager;
    private SheetsLogger   sheetsLogger;

    private EditText etLandmark;
    private Button   btnScan, btnContinuous;
    private TextView tvStatus, tvCount, tvRttCount;

    private int  totalRows   = 0;
    private int  totalScans  = 0;
    private boolean continuous   = false;
    private final Handler contHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLandmark   = findViewById(R.id.etLandmark);
        btnScan      = findViewById(R.id.btnScan);
        btnContinuous= findViewById(R.id.btnContinuous);
        tvStatus     = findViewById(R.id.tvStatus);
        tvCount      = findViewById(R.id.tvCount);
        tvRttCount   = findViewById(R.id.tvRttCount);

        wifiManager  = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        rttManager   = (WifiRttManager) getSystemService(Context.WIFI_RTT_RANGING_SERVICE);
        sheetsLogger = new SheetsLogger();

        // Request permissions
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_WIFI_STATE
        }, 100);

        btnScan.setOnClickListener(v -> triggerScan());

        btnContinuous.setOnClickListener(v -> {
            if (!continuous) {
                if (getLandmark().isEmpty()) {
                    tvStatus.setText("⚠️ Enter a landmark label first");
                    return;
                }
                continuous = true;
                btnContinuous.setText("⏹ Stop Continuous");
                btnScan.setEnabled(false);
                scheduleContinuous();
            } else {
                stopContinuous();
            }
        });
    }

    private String getLandmark() {
        return etLandmark.getText().toString().trim();
    }

    private void stopContinuous() {
        continuous = false;
        contHandler.removeCallbacksAndMessages(null);
        btnContinuous.setText("▶ Start Continuous (3s)");
        btnScan.setEnabled(true);
        tvStatus.setText("Continuous stopped. Total scans: " + totalScans);
    }

    private void scheduleContinuous() {
        contHandler.postDelayed(() -> {
            if (continuous) {
                triggerScan();
                scheduleContinuous();
            }
        }, 3000);
    }

    private void triggerScan() {
        String landmark = getLandmark();
        if (landmark.isEmpty()) {
            tvStatus.setText("⚠️ Enter a landmark label before scanning");
            return;
        }

        tvStatus.setText("📡 Scanning WiFi...");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                unregisterReceiver(this);
                List<ScanResult> results = wifiManager.getScanResults();
                processScanResults(results, landmark);
            }
        };

        registerReceiver(receiver,
                new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
    }

    private void processScanResults(List<ScanResult> scanResults, String landmark) {
        long timestamp = System.currentTimeMillis();
        totalScans++;

        // Separate into RTT-capable and RSS-only
        List<ScanResult> rttAPs = new ArrayList<>();
        List<ScanResult> rssAPs = new ArrayList<>();

        for (ScanResult sr : scanResults) {
            if (!APDatabase.contains(sr.BSSID)) continue; // ignore non-Iribe APs

            APLocation ap = APDatabase.get(sr.BSSID);
            if (ap.rttCapable && sr.is80211mcResponder()) {
                rttAPs.add(sr);
            } else {
                rssAPs.add(sr);
            }
        }

        // Log RSS-only APs immediately
        for (ScanResult sr : rssAPs) {
            APLocation ap = APDatabase.get(sr.BSSID);
            if (ap == null) continue;
            sheetsLogger.log(timestamp, landmark,
                    sr.BSSID, -1, -1, sr.level,
                    0, 0, "RSS_ONLY", ap.x, ap.y);
            totalRows++;
        }

        // Also log RSS from RTT APs (before ranging)
        for (ScanResult sr : rttAPs) {
            APLocation ap = APDatabase.get(sr.BSSID);
            if (ap == null) continue;
            sheetsLogger.log(timestamp, landmark,
                    sr.BSSID, -1, -1, sr.level,
                    0, 0, "RSS_PRE_RTT", ap.x, ap.y);
            totalRows++;
        }

        int rttCount = Math.min(rttAPs.size(), RangingRequest.getMaxPeers());

        runOnUiThread(() -> {
            tvStatus.setText("📶 Found " + scanResults.size() + " APs total\n"
                    + "✅ Iribe RSS APs: " + rssAPs.size() + "\n"
                    + "📡 Iribe RTT APs: " + rttAPs.size()
                    + " (ranging " + rttCount + ")");
            tvRttCount.setText("RTT capable: " + rttAPs.size());
        });

        // Perform RTT ranging if available
        if (!rttAPs.isEmpty() && rttManager != null) {
            performRTT(rttAPs, landmark, timestamp);
        } else {
            updateCount();
        }
    }

    private void performRTT(List<ScanResult> rttAPs, String landmark, long timestamp) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) return;

        RangingRequest.Builder builder = new RangingRequest.Builder();
        int added = 0;
        for (ScanResult sr : rttAPs) {
            if (added >= RangingRequest.getMaxPeers()) break;
            builder.addAccessPoint(sr);
            added++;
        }

        rttManager.startRanging(builder.build(), getMainExecutor(),
                new RangingResultCallback() {
                    @Override
                    public void onRangingResults(List<RangingResult> results) {
                        for (RangingResult r : results) {
                            String bssid = r.getMacAddress().toString();
                            APLocation ap = APDatabase.get(bssid);
                            if (ap == null) continue;

                            if (r.getStatus() == RangingResult.STATUS_SUCCESS) {
                                sheetsLogger.log(timestamp, landmark,
                                        bssid,
                                        r.getDistanceMm(),
                                        r.getDistanceStdDevMm(),
                                        r.getRssi(),
                                        r.getNumSuccessfulMeasurements(),
                                        r.getNumAttemptedMeasurements(),
                                        "RTT+RSS", ap.x, ap.y);
                            } else {
                                sheetsLogger.log(timestamp, landmark,
                                        bssid, -1, -1, -100,
                                        0, 0, "RTT_FAILED", ap.x, ap.y);
                            }
                            totalRows++;
                        }
                        updateCount();
                        runOnUiThread(() ->
                                tvStatus.append("\n✅ RTT ranging complete"));
                    }

                    @Override
                    public void onRangingFailure(int code) {
                        runOnUiThread(() ->
                                tvStatus.append("\n⚠️ RTT ranging failed (code " + code + ")"));
                        updateCount();
                    }
                });
    }

    private void updateCount() {
        runOnUiThread(() -> {
            tvCount.setText("Rows sent: " + totalRows
                    + "  |  Scans: " + totalScans);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopContinuous();
    }
}
