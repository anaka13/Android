package com.example.assignment2.models;

public class day {
    private double avgtemp_c;
    private int avghumidity;
    private condition condition;

    public String getTemp(){
        return Integer.toString((int)avgtemp_c)+"°C";
    }

    public String getHumidity(){
        return Integer.toString(avghumidity)+"%";
    }

    public condition getCondition(){
        return condition;
    }


}
