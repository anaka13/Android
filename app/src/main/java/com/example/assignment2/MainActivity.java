package com.example.assignment2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.assignment2.models.Country;
import com.example.assignment2.models.mainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    private ViewPager viewPager;
    private Retrofit retrofit;
    private Api api;
    public List<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        api.getCountries("name").enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                    countries = response.body();
                    viewPager = findViewById(R.id.weather_view_pager);
                    adapter = new ViewPagerAdapter(getSupportFragmentManager(), countries);
                    viewPager.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("Response",t.getMessage());
            }
        });
    }
}
