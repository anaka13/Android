package com.example.assignment2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ForecastViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public TextView temperature;
    public ImageView icon;
    public TextView humidity;


    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.item_date_text);
        temperature = itemView.findViewById(R.id.item_temperature_text);
        icon = itemView.findViewById(R.id.item_forecast_icon);
        humidity = itemView.findViewById(R.id.item_humidity_text);


    }
}
