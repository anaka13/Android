package com.example.assignment2.models;

public class astro {
    private String sunrise;
    private String sunset;

    public String getSunriseSunset(){
        return sunrise.replaceAll("\\s+","").toLowerCase()+sunset.replaceAll("\\s+","").toLowerCase();
    }
}
