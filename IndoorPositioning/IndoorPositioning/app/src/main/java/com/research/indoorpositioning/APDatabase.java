package com.research.indoorpositioning;

import java.util.HashMap;
import java.util.Map;

/**
 * Complete AP lookup table — Iribe Center Floor 5
 * 34 RTT-capable APs (umd-irb-test, 5GHz) — rttCapable = true
 * 34 RSS-only APs (umd-iot/umd-guest/eduroam)  — rttCapable = false
 */
public class APDatabase {

    private static final Map<String, APLocation> DB = new HashMap<>();

    static {
        // ── RTT-CAPABLE APs (umd-irb-test, 5GHz) ────────────────
        DB.put("98:8f:00:5c:c2:03", new APLocation("98:8f:00:5c:c2:03", 64.1f,   -103.59f, true));  // ap5105
        DB.put("98:8f:00:5c:c1:73", new APLocation("98:8f:00:5c:c1:73", 57.36f,  -107.9f,  true));  // ap5108 (estimated)
        DB.put("50:e4:e0:3b:5a:53", new APLocation("50:e4:e0:3b:5a:53", 61.45f,  -115.44f, true));  // ap5112-1
        DB.put("50:e4:e0:39:a8:73", new APLocation("50:e4:e0:39:a8:73", 53.27f,  -100.36f, true));  // ap5112-2
        DB.put("50:e4:e0:39:ec:b3", new APLocation("50:e4:e0:39:ec:b3", 36.77f,  -53.09f,  true));  // ap5113
        DB.put("50:e4:e0:3b:9e:f3", new APLocation("50:e4:e0:3b:9e:f3", 43.5f,   -98.6f,   true));  // ap5116-1
        DB.put("50:e4:e0:3b:1a:33", new APLocation("50:e4:e0:3b:1a:33", 42.14f,  -76.33f,  true));  // ap5116-2
        DB.put("98:8f:00:5d:0e:23", new APLocation("98:8f:00:5d:0e:23", 47.42f,  -97.11f,  true));  // ap5119
        DB.put("50:e4:e0:3d:26:a3", new APLocation("50:e4:e0:3d:26:a3", 29.67f,  -75.75f,  true));  // ap5124
        DB.put("98:8f:00:5d:3a:c3", new APLocation("98:8f:00:5d:3a:c3", 22.5f,   -70.35f,  true));  // ap5128
        DB.put("98:8f:00:5c:d6:43", new APLocation("98:8f:00:5c:d6:43", 9.79f,   -47.47f,  true));  // ap5134
        DB.put("98:8f:00:5c:87:b3", new APLocation("98:8f:00:5c:87:b3", 4.93f,   -14.91f,  true));  // ap5136
        DB.put("98:8f:00:5c:a2:83", new APLocation("98:8f:00:5c:a2:83", 15.46f,  -36.35f,  true));  // ap5137
        DB.put("98:8f:00:5d:27:d3", new APLocation("98:8f:00:5d:27:d3", 15.58f,  -19.93f,  true));  // ap5144
        DB.put("50:e4:e0:3d:27:c3", new APLocation("50:e4:e0:3d:27:c3", 25.94f,  -33.71f,  true));  // ap5150
        DB.put("50:e4:e0:3a:81:c3", new APLocation("50:e4:e0:3a:81:c3", 39.57f,  -44.94f,  true));  // ap5158
        DB.put("50:e4:e0:39:19:93", new APLocation("50:e4:e0:39:19:93", 48.19f,  -68.35f,  true));  // ap5161
        DB.put("50:e4:e0:3c:6b:23", new APLocation("50:e4:e0:3c:6b:23", 48.45f,  -46.18f,  true));  // ap5164
        DB.put("98:8f:00:5c:dc:43", new APLocation("98:8f:00:5c:dc:43", 52.39f,  -67.54f,  true));  // ap5165
        DB.put("98:8f:00:5c:f1:73", new APLocation("98:8f:00:5c:f1:73", 57.61f,  -80.39f,  true));  // ap5104(4b)
        DB.put("98:8f:00:5d:21:13", new APLocation("98:8f:00:5d:21:13", 69.74f,  -97.95f,  true));  // ap5204
        DB.put("98:8f:00:5c:f4:43", new APLocation("98:8f:00:5c:f4:43", 74.55f,  -90.28f,  true));  // ap5207
        DB.put("98:8f:00:5d:2d:d3", new APLocation("98:8f:00:5d:2d:d3", 64.25f,  -58.84f,  true));  // ap5207a
        DB.put("98:8f:00:5d:00:13", new APLocation("98:8f:00:5d:00:13", 71.01f,  -59.87f,  true));  // ap5208
        DB.put("50:e4:e0:39:9e:53", new APLocation("50:e4:e0:39:9e:53", 80.6f,   -72.86f,  true));  // ap5216
        DB.put("50:e4:e0:3d:31:e3", new APLocation("50:e4:e0:3d:31:e3", 85.83f,  -60.11f,  true));  // ap5222
        DB.put("98:8f:00:5c:8f:63", new APLocation("98:8f:00:5c:8f:63", 89.64f,  -52.2f,   true));  // ap5228
        DB.put("98:8f:00:5c:df:13", new APLocation("98:8f:00:5c:df:13", 95.93f,  -47.63f,  true));  // ap5231
        DB.put("98:8f:00:5d:06:d3", new APLocation("98:8f:00:5d:06:d3", 86.99f,  -22.31f,  true));  // ap5234
        DB.put("98:8f:00:5c:ae:83", new APLocation("98:8f:00:5c:ae:83", 79.64f,  -2.55f,   true));  // ap5242
        DB.put("50:e4:e0:39:32:b3", new APLocation("50:e4:e0:39:32:b3", 73.46f,  -13.54f,  true));  // ap5245a
        DB.put("50:e4:e0:3d:a0:e3", new APLocation("50:e4:e0:3d:a0:e3", 81.12f,  -37.95f,  true));  // ap5246
        DB.put("50:e4:e0:3a:59:73", new APLocation("50:e4:e0:3a:59:73", 69.97f,  -16.66f,  true));  // ap5250
        DB.put("98:8f:00:5c:da:63", new APLocation("98:8f:00:5c:da:63", 66.97f,  -22.63f,  true));  // ap5256

        // ── RSS-ONLY APs ──────────────────────────────────────────
        DB.put("98:8f:00:5c:87:b1", new APLocation("98:8f:00:5c:87:b1", 4.93f,   -14.91f,  false)); // ap5136
        DB.put("98:8f:00:5c:d6:20", new APLocation("98:8f:00:5c:d6:20", 9.79f,   -47.47f,  false)); // ap5134
        DB.put("98:8f:00:5d:27:d0", new APLocation("98:8f:00:5d:27:d0", 15.58f,  -19.93f,  false)); // ap5144
        DB.put("98:8f:00:5c:d6:41", new APLocation("98:8f:00:5c:d6:41", 15.46f,  -36.35f,  false)); // ap5137
        DB.put("98:8f:00:5d:3a:a2", new APLocation("98:8f:00:5d:3a:a2", 22.5f,   -70.35f,  false)); // ap5128
        DB.put("50:e4:e0:3d:27:d1", new APLocation("50:e4:e0:3d:27:d1", 25.94f,  -33.71f,  false)); // ap5150
        DB.put("50:e4:e0:3d:26:a0", new APLocation("50:e4:e0:3d:26:a0", 29.67f,  -75.75f,  false)); // ap5124
        DB.put("50:e4:e0:3b:9e:f0", new APLocation("50:e4:e0:3b:9e:f0", 43.5f,   -98.6f,   false)); // ap5116-1
        DB.put("98:8f:00:5d:0e:22", new APLocation("98:8f:00:5d:0e:22", 47.42f,  -97.11f,  false)); // ap5119
        DB.put("98:8f:00:5d:0e:00", new APLocation("98:8f:00:5d:0e:00", 42.14f,  -76.33f,  false)); // ap5116-2
        DB.put("50:e4:e0:39:ec:b1", new APLocation("50:e4:e0:39:ec:b1", 36.77f,  -53.09f,  false)); // ap5113
        DB.put("50:e4:e0:3a:81:c0", new APLocation("50:e4:e0:3a:81:c0", 39.57f,  -44.94f,  false)); // ap5158
        DB.put("50:e4:e0:39:a8:80", new APLocation("50:e4:e0:39:a8:80", 53.27f,  -100.36f, false)); // ap5112-2
        DB.put("98:8f:00:5c:a2:61", new APLocation("98:8f:00:5c:a2:61", 61.45f,  -115.44f, false)); // ap5112-1
        DB.put("50:e4:e0:39:19:90", new APLocation("50:e4:e0:39:19:90", 48.19f,  -68.35f,  false)); // ap5161
        DB.put("98:8f:00:5c:dc:41", new APLocation("98:8f:00:5c:dc:41", 52.39f,  -67.54f,  false)); // ap5165
        DB.put("50:e4:e0:3c:6b:20", new APLocation("50:e4:e0:3c:6b:20", 48.45f,  -46.18f,  false)); // ap5164
        DB.put("98:8f:00:5c:c2:00", new APLocation("98:8f:00:5c:c2:00", 64.1f,   -103.59f, false)); // ap5105
        DB.put("98:8f:00:5c:c2:02", new APLocation("98:8f:00:5c:c2:02", 57.61f,  -80.39f,  false)); // ap5104(4b)
        DB.put("98:8f:00:5c:f1:71", new APLocation("98:8f:00:5c:f1:71", 69.74f,  -97.95f,  false)); // ap5204
        DB.put("98:8f:00:5d:21:10", new APLocation("98:8f:00:5d:21:10", 74.55f,  -90.28f,  false)); // ap5207
        DB.put("98:8f:00:5c:f4:20", new APLocation("98:8f:00:5c:f4:20", 64.25f,  -58.84f,  false)); // ap5207a
        DB.put("98:8f:00:5c:da:62", new APLocation("98:8f:00:5c:da:62", 55.65f,  -32.69f,  false)); // ap5256
        DB.put("98:8f:00:5c:da:61", new APLocation("98:8f:00:5c:da:61", 71.01f,  -59.87f,  false)); // ap5208
        DB.put("98:8f:00:5d:00:11", new APLocation("98:8f:00:5d:00:11", 80.6f,   -72.86f,  false)); // ap5216
        DB.put("50:e4:e0:39:9e:61", new APLocation("50:e4:e0:39:9e:61", 85.83f,  -60.11f,  false)); // ap5222
        DB.put("50:e4:e0:3d:31:f1", new APLocation("50:e4:e0:3d:31:f1", 89.64f,  -52.2f,   false)); // ap5228
        DB.put("98:8f:00:5c:8f:40", new APLocation("98:8f:00:5c:8f:40", 95.93f,  -47.63f,  false)); // ap5231
        DB.put("98:8f:00:5c:de:f1", new APLocation("98:8f:00:5c:de:f1", 86.99f,  -22.31f,  false)); // ap5234
        DB.put("98:8f:00:5d:06:b2", new APLocation("98:8f:00:5d:06:b2", 79.64f,  -2.55f,   false)); // ap5242
        DB.put("50:e4:e0:39:32:c2", new APLocation("50:e4:e0:39:32:c2", 73.46f,  -13.54f,  false)); // ap5245a
        DB.put("50:e4:e0:3d:a0:f0", new APLocation("50:e4:e0:3d:a0:f0", 81.12f,  -37.95f,  false)); // ap5246
        DB.put("98:8f:00:5c:ae:81", new APLocation("98:8f:00:5c:ae:81", 69.97f,  -16.66f,  false)); // ap5250
        DB.put("50:e4:e0:3a:59:70", new APLocation("50:e4:e0:3a:59:70", 66.97f,  -22.63f,  false)); // ap5256
    }

    public static APLocation get(String bssid) {
        return DB.get(bssid);
    }

    public static boolean contains(String bssid) {
        return DB.containsKey(bssid);
    }

    public static Map<String, APLocation> getAll() {
        return DB;
    }
}
