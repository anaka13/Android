package com.example.assignment2;

import com.example.assignment2.models.Country;
import com.example.assignment2.models.mainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("rest/v2/all")
    Call<List<Country>> getCountries(@Query("fields") String field);

    @GET("v1/forecast.json")
    Call<mainModel> getCountryInfo(@Query("q") String countryName, @Query("key") String key, @Query("days") int numDays);
}
