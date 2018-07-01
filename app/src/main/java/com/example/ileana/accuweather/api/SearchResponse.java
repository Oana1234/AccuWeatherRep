package com.example.ileana.accuweather.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Oana-Maria on 19/04/2018.
 */

public class SearchResponse {

    @SerializedName("cod")
    private String cod;

    @SerializedName("list")
    private List<SearchLocation> searchLocations;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public List<SearchLocation> getSearchLocations() {
        return searchLocations;
    }

    public void setSearchLocations(List<SearchLocation> searchLocations) {
        this.searchLocations = searchLocations;
    }
}
