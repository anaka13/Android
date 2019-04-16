package com.example.assignment2.models;

public class currentWeather {
    private double temp_c;
    private condition condition;
    private double wind_kph;
    private int humidity;
    private double precip_mm;
    private double feelslike_c;
    private int is_day;

    public String getTemp(){
        return Integer.toString((int) temp_c)+"°C";
    }

    public condition getCondition(){
        return condition;
    }

    public String getWindSpeed(){
        return Double.toString(wind_kph)+"kmh";
    }

    public String getPrecipitation(){
        return Double.toString(precip_mm)+"%";
    }

    public String getHumidity(){
        return Integer.toString(humidity)+"%";
    }

    public String getPerceived(){
        return Integer.toString((int)feelslike_c)+"°C";
    }

    public boolean getIsDay(){
        if(is_day == 1) return true;
        return false;
    }

}
