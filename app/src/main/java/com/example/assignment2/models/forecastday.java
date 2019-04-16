package com.example.assignment2.models;

public class forecastday {
    private String date;
    private day day;
    private astro astro;

    public String getEpoch(){
        return date;
    }

    public day getDay(){
        return day;
    }

    public astro getAstro(){
        return astro;
    }
}
