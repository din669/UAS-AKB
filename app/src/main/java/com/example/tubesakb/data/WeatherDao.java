package com.example.tubesakb.data;

import com.example.tubesakb.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherDao {
    @GET("current.json")
    Call<WeatherResponse> getWeatherFromAPI(@Query("q") String city, @Query("key") String key, @Query("aqi") String aqi );
}
