package com.research.indoorpositioning;

import android.util.Log;
import org.json.JSONObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.*;

public class SheetsLogger {

    // ── PASTE YOUR APPS SCRIPT WEB APP URL HERE ──────────────────
    public static final String SHEETS_URL = "YOUR_APPS_SCRIPT_URL_HERE";
    // ─────────────────────────────────────────────────────────────

    private static final String TAG = "SheetsLogger";
    private final OkHttpClient client = new OkHttpClient();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void log(long timestamp, String landmark,
                    String bssid, int distanceMm, int stdDevMm,
                    int rssi, int successFrames, int attemptFrames,
                    String type, float apX, float apY) {
        executor.execute(() -> {
            try {
                JSONObject json = new JSONObject();
                json.put("timestamp",       timestamp);
                json.put("landmark",        landmark);
                json.put("bssid",           bssid);
                json.put("distance_mm",     distanceMm);
                json.put("stddev_mm",       stdDevMm);
                json.put("rssi_dbm",        rssi);
                json.put("success_frames",  successFrames);
                json.put("attempt_frames",  attemptFrames);
                json.put("type",            type);
                json.put("ap_x",            apX);
                json.put("ap_y",            apY);

                RequestBody body = RequestBody.create(
                        json.toString(),
                        MediaType.get("application/json; charset=utf-8"));

                Request request = new Request.Builder()
                        .url(SHEETS_URL)
                        .post(body)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    Log.d(TAG, "Logged: " + bssid + " → " + response.code());
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to log: " + e.getMessage());
            }
        });
    }
}
