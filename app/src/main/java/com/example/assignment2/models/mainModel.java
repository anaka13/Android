package com.example.assignment2.models;

import com.google.gson.annotations.SerializedName;

public class mainModel {
    private location location;
    private currentWeather current;
    private forecast forecast;

    public location getLocation(){
        return location;
    }

    public currentWeather getCurrentWeather(){
        return current;
    }

    public forecast getForecast(){
        return forecast;
    }
}
