package com.example.ileana.accuweather.models;

/**
 * Created by Oana-Maria on 17/04/2018.
 */

public class LocationWeather {

    private final Location location;
    private final WeatherCondition weatherCondition;

    public LocationWeather(Location location, WeatherCondition weatherCondition) {
        this.location = location;
        this.weatherCondition = weatherCondition;
    }

    public Location getLocation() {
        return location;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

}
