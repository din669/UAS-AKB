package com.example.tubesakb.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tubesakb.data.WeatherApi;
import com.example.tubesakb.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherImpl implements WeatherInterface {

    private WeatherListener weatherListener;

    public void setWeatherListener(WeatherListener weatherListener) {
        this.weatherListener = weatherListener;
    }

    @Override
    public void getWeather(String city) {
        WeatherApi.instance
                .getWeatherFromAPI(city, "155d5b8f48284953ada23438221308", "yes")
                .enqueue(
                        new Callback<WeatherResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                                if (response.isSuccessful()) {
                                    weatherListener.onSuccess(response.body());
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                                weatherListener.onFailed(t.getMessage());
                            }
                        }
                );
    }

    public interface WeatherListener {
        void onSuccess(WeatherResponse result);
        void onFailed(String errorMessage);
    }
}


