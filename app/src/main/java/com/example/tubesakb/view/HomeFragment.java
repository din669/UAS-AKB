package com.example.tubesakb.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tubesakb.R;
import com.example.tubesakb.model.WeatherResponse;
import com.example.tubesakb.presenter.WeatherImpl;
import com.example.tubesakb.view.adapter.CarouselAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    static int NUM_PAGES = 2;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 700;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    private ViewPager screenPager;
    private CarouselAdapter adapter;
    private BottomSheetFragment bottomSheet = new BottomSheetFragment();

    WeatherImpl weatherImpl;
    TextView weatherText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        weatherImpl = new WeatherImpl();
        weatherImpl.getWeather("bandung");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        screenPager = view.findViewById(R.id.view_main_banner);
        adapter = new CarouselAdapter(requireContext());

        screenPager.setAdapter(adapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                    screenPager.setCurrentItem(currentPage, true);

                } else {
                    currentPage++;
                    screenPager.setCurrentItem(currentPage, true);

                }
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);

        Button buttonkota = view.findViewById(R.id.main_btn_kota);

        buttonkota.setOnClickListener(v -> {
            String selectedkota = buttonkota.getText().toString();
            bottomSheet = BottomSheetFragment.newInstance(selectedkota);
        bottomSheet.setondismisslistener(new BottomSheetFragment.OnDismisslistener() {
                @Override
                public void onDismiss(String kota) {
                    buttonkota.setText(kota);
                    bottomSheet.dismiss();
                }
            });
        bottomSheet.show(requireActivity().getSupportFragmentManager(), "");
        });

        view.findViewById(R.id.card_alam).setOnClickListener(v->{
            Intent intent = new Intent(requireContext(), DetailListAlamActivity.class);
            intent.putExtra("extra_kota", buttonkota.getText());
            startActivity(intent);
        });
        view.findViewById(R.id.card_rekreasi).setOnClickListener(v->{
            Intent intent = new Intent(requireContext(), DetailListRekreasiActivity.class);
            intent.putExtra("extra_kota", buttonkota.getText());
            startActivity(intent);
        });
        view.findViewById(R.id.card_kuliner).setOnClickListener(v->{
            Intent intent = new Intent(requireContext(), DetailListKulinerActivity.class);
            intent.putExtra("extra_kota", buttonkota.getText());
            startActivity(intent);
        });
        view.findViewById(R.id.card_belanja).setOnClickListener(v->{
            Intent intent = new Intent(requireContext(), DetailListBelanjaActivity.class);
            intent.putExtra("extra_kota", buttonkota.getText());
            startActivity(intent);
        });

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

    @Override
    public void onPause() {
        super.onPause();
        if (bottomSheet.isVisible()){
            bottomSheet.dismiss();
        }
    }
}
