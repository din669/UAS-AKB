package com.example.tubesakb.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubesakb.R;
import com.example.tubesakb.model.WeatherResponse;
import com.example.tubesakb.presenter.WeatherImpl;

public class BerandaFragment extends Fragment{

    public BerandaFragment(){}
    WeatherImpl weatherImpl;
    TextView weatherText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weatherImpl = new WeatherImpl();
        weatherImpl.getWeather("bandung");
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherText = view.findViewById(R.id.weather_txt);

        weatherImpl.setWeatherListener(new WeatherImpl.WeatherListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(WeatherResponse result) {
                weatherText.setText(result.getCurrent().getCondition().getText() +" "+result.getCurrent().getTempC() +"\u2103");
            }

            @Override
            public void onFailed(String errorMessage) {
                Log.d("Info Log", "onFailed: " + errorMessage);
            }
        });
    }
}

