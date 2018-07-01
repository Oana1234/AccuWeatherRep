package com.example.ileana.accuweather.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Oana-Maria on 19/04/2018.
 */

public class Coord {

    @SerializedName("lat")
    private double latitude;

    @SerializedName("long")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
