package com.example.ileana.accuweather.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by Oana-Maria on 08/03/2018.
 */

@Entity(tableName = "locations")
public class Location {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private boolean active;
    private double latitude;
    private double longitude;
    private String country;


    @Ignore
    public Location(String name) {
    //   this(UUID.randomUUID().toString(),name, false));

    }

    @Ignore
    public Location(String name, boolean active, @NonNull  String id) {
        this.name = name;
        this.active = active;
        this.id = id;
    }

    public Location(@NonNull String id, String name, boolean active, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.country = country;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}