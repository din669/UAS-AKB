package com.example.tubesakb.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.tubesakb.R;
import com.example.tubesakb.view.adapter.ViewpagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Viewpager extends AppCompatActivity {

    private ViewPager screenPager;
    private ViewpagerAdapter adapter;
    private Button buttonGetStarted;
    TabLayout tabIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        tabIndicator = findViewById(R.id.tab_indicator);
        buttonGetStarted = findViewById(R.id.buttonnext);
        screenPager = findViewById(R.id.view_pager_image);
        adapter = new ViewpagerAdapter(this);

        screenPager.setAdapter(adapter);
        tabIndicator.setupWithViewPager(screenPager);
        buttonGetStarted.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }
}
