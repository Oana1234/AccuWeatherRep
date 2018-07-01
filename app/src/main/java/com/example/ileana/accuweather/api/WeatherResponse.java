package com.example.ileana.accuweather.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ILEANA on 4/12/2018.
 */

public class WeatherResponse {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private Integer cod;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }

}
