package com.example.ileana.accuweather.managers;

import android.content.Context;

import com.example.ileana.accuweather.api.WeatherApi;
import com.example.ileana.accuweather.models.WeatherCondition;

import java.util.List;

/**
 * Created by ILEANA on 5/3/2018.
 */

public class WeatherService {

    private static WeatherService weatherService = null;
    private final WeatherApi weatherApi;

    public WeatherService(Context context) {
        this.weatherApi = new WeatherApi(context);
    }

    public WeatherCondition getCurrentWeather(double latitude, double longitude) {
        return weatherApi.getCurrentWeather(latitude, longitude);
    }

    public List<WeatherCondition> getWeatherConditionForecast(double latitude, double longitude) {
        return weatherApi.getWeatherForecast(latitude, longitude);
    }

    public static WeatherService getInstance(Context context) {
        if (weatherService == null) {
            weatherService = new WeatherService(context);
        }
        return weatherService;
    }


}
