package com.example.ileana.accuweather.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Oana-Maria on 19/04/2018.
 */

public class SearchLocation {


    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("coord")
    private Coord coord;

    public Coord getCoord() {
        return coord;
    }

    @SerializedName("sys")
    private Country country;

    public String  getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return this.country.getCountry();
    }

    public class Country{

        @SerializedName("country")
        private String country;

        public String getCountry() {
            return country;
        }
    }
}
