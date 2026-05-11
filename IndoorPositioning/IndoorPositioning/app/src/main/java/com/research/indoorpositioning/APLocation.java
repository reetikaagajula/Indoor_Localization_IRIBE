package com.research.indoorpositioning;

public class APLocation {
    public final String bssid;
    public final float x;
    public final float y;
    public final boolean rttCapable;

    public APLocation(String bssid, float x, float y, boolean rttCapable) {
        this.bssid      = bssid;
        this.x          = x;
        this.y          = y;
        this.rttCapable = rttCapable;
    }
}
