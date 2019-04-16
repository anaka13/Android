package com.example.assignment2.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class location {
    private String country;
    private String localtime;

    public String getCountry(){
        return country;
    }

    public String getEpoch(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = format.format( new Date());
        Date date = null;
        try {
            date = format.parse( localtime );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("EEEE dd MMM yyyy HH:mm a");
        return formatter.format(date);
    }
}
