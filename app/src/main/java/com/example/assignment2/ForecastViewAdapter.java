package com.example.assignment2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2.models.forecastday;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForecastViewAdapter extends RecyclerView.Adapter<ForecastViewHolder> {
    private List<forecastday> forecastdays;
    private Context context;

    public ForecastViewAdapter(List<forecastday> data, Context context) {
        this.forecastdays = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forecast_list, viewGroup, false);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder forecastViewHolder, int i) {
        forecastday currentDay = forecastdays.get(i);
        forecastViewHolder.temperature.setText(currentDay.getDay().getTemp());
        forecastViewHolder.date.setText(currentDay.getEpoch());
        forecastViewHolder.humidity.setText(currentDay.getDay().getHumidity());
        Picasso.with(context).load(currentDay.getDay().getCondition().getIconAddress()).into(forecastViewHolder.icon);

    }

    @Override
    public int getItemCount() {
        return forecastdays.size();
    }
}
