package com.example.ileana.accuweather.models;

import java.util.Date;

/**
 * Created by ILEANA on 2/27/2018.
 */

public class WeatherCondition {


    private String city;
    private Date date;

    private double temp;
    private double tempMin;
    private double tempMax;

    public WeatherCondition() {
    }

    public WeatherCondition(String city, Date date, double temp, double tempMin, double tempMax) {
        this.city = city;
        this.date = date;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
