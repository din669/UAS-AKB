package com.example.tubesakb.data;

import com.example.tubesakb.presenter.WeatherImpl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApi {

    public static WeatherDao instance = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherDao.class);
}
