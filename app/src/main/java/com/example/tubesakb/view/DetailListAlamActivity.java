package com.example.tubesakb.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubesakb.R;
import com.example.tubesakb.presenter.DaftarAlamImpl;
import com.example.tubesakb.view.adapter.DataAlamAdapter;

import java.util.ArrayList;

public class DetailListAlamActivity extends AppCompatActivity {

    String kota;
    RecyclerView recyclerView;
    DataAlamAdapter dataAlamAdapter;
    DaftarAlamImpl dataAlamImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        recyclerView = findViewById(R.id.list_data_alam);
        kota = getIntent().getStringExtra("extra_kota");

        dataAlamImpl = new DaftarAlamImpl();
        dataAlamImpl.getDataAlam(kota);
        dataAlamImpl.setDataAlamListener(result -> {
            dataAlamAdapter = new DataAlamAdapter(getApplicationContext(), new ArrayList(result));
            recyclerView.setAdapter(dataAlamAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
            recyclerView.setHasFixedSize(true);

            dataAlamAdapter.setOnitemClick(data -> {
                Intent intent = new Intent(getApplicationContext(), DetailAlamActivity.class);
                intent.putExtra("extra_nama_data_alam", data.getNama());
                intent.putExtra("extra_desc_data_alam", data.getDesc());
                intent.putExtra("extra_img_data_alam", data.getImg());
                intent.putExtra("extra_lat_data_alam", data.getLatLng().latitude);
                intent.putExtra("extra_lng_data_alam", data.getLatLng().longitude);
                startActivity(intent);
            });
        });
    }
}
