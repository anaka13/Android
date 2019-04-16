package com.example.assignment2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.models.Country;
import com.example.assignment2.models.mainModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class weatherFragment extends Fragment {

    private Retrofit retrofit;
    private Api api;
    private Country country;
    private mainModel model;
    private ConstraintLayout gradientConstraintLayout;
    private TextView countryName;
    private TextView date;
    private TextView temperature;
    private ImageView weatherImage;
    private ImageView precipImage;
    private TextView precipText;
    private ImageView humidityImage;
    private TextView humidityText;
    private ImageView windSpeedImage;
    private TextView windSpeedText;
    private TextView dayAndNightText;
    private RecyclerView recyclerView;
    private TextView perceivedText;
    private ForecastViewAdapter adapter;
    private View iconsView;
    private TextView loadingText;

    public static weatherFragment newInstance() {
        return new weatherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        country = (Country) getArguments().getSerializable("country");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_weather,container,false);
        return view;
    }

    public void onViewCreated (View view, Bundle savedInstanceState){
        loadingText = view.findViewById(R.id.loading_text);
        gradientConstraintLayout = view.findViewById(R.id.gradient_layout);
        countryName= view.findViewById(R.id.country_name_textview);
        loadingText.setText("Waiting for " + country.getName()+" to load.");
        date=view.findViewById(R.id.date_textview);
        temperature= view.findViewById(R.id.dynamic_degree_text);
        weatherImage= view.findViewById(R.id.weather_icon);
        precipImage= view.findViewById(R.id.precipitation_icon);
        precipText=  view.findViewById(R.id.icons).findViewById(R.id.percepitation).findViewById(R.id.precipitation_dynamic_text);
        humidityImage= view.findViewById(R.id.humidity_icon);
        humidityText= view.findViewById(R.id.icons).findViewById(R.id.humidity).findViewById(R.id.humidity_dynamic_text);
        perceivedText = view.findViewById(R.id.perceived_text);
        windSpeedImage= view.findViewById(R.id.windspeed_icon);
        windSpeedText= view.findViewById(R.id.icons).findViewById(R.id.windspeed).findViewById(R.id.windspeed_dynamic_text);
        dayAndNightText= view.findViewById(R.id.icons).findViewById(R.id.dayNnight).findViewById(R.id.day_n_night_dynamic_text);
        recyclerView= view.findViewById(R.id.daily_forecast);
        iconsView = view.findViewById(R.id.icons_layout);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getCountryInfo(country.getName(),"0620cbec8e2b4723a70192109191104", 10).enqueue(new Callback<mainModel>() {
            @Override
            public void onResponse(Call<mainModel> call, Response<mainModel> response) {
                if(response.isSuccessful()){
                    model = response.body();
                    loadingText.setVisibility(View.INVISIBLE);
                    countryName.setText(country.getName());
                    date.setText(model.getLocation().getEpoch());
                    temperature.setText(model.getCurrentWeather().getTemp());
                    perceivedText.setText("Perceived "+ model.getCurrentWeather().getPerceived() );
                    windSpeedText.setText(model.getCurrentWeather().getWindSpeed());
                    precipText.setText(model.getCurrentWeather().getPrecipitation());
                    humidityText.setText(model.getCurrentWeather().getHumidity());
                    iconsView.setVisibility(View.VISIBLE);
                    if (model.getForecast().getForecast().size() > 0)
                        dayAndNightText.setText(model.getForecast().getForecast().get(0).getAstro().getSunriseSunset());
                    if (model.getCurrentWeather().getCondition() != null) {
                        Picasso.with(getActivity()).load(model.getCurrentWeather().getCondition().getIconAddress()).into(weatherImage);
                    }
                    if (!model.getCurrentWeather().getIsDay()){
                        humidityImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.tint_color));
                        windSpeedImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.tint_color));
                        precipImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.tint_color));
                        gradientConstraintLayout.setBackgroundResource(R.drawable.background_nightlight_gradient);
                    } else {
                        gradientConstraintLayout.setBackgroundResource(R.drawable.background_daylight_gradient);
                    }
                    adapter = new ForecastViewAdapter(model.getForecast().getForecast(), getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<mainModel> call, Throwable t) {
                Log.d("error", t.getMessage() + "  " + country.getName());
            }
        });
    }


}
