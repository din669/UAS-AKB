package com.example.tubesakb.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubesakb.R;
import com.example.tubesakb.presenter.DaftarKulinerImpl;
import com.example.tubesakb.view.adapter.DataKulinerAdapter;

import java.util.ArrayList;

public class DetailListKulinerActivity extends AppCompatActivity {

    String kota;
    RecyclerView recyclerView;
    DataKulinerAdapter dataKulinerAdapter;
    DaftarKulinerImpl dataKulinerImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        recyclerView = findViewById(R.id.list_data_kuliner);
        kota = getIntent().getStringExtra("extra_kota");

        dataKulinerImpl = new DaftarKulinerImpl();
        dataKulinerImpl.getDataKuliner(kota);
        dataKulinerImpl.setDataKulinerListener(result -> {
            dataKulinerAdapter = new DataKulinerAdapter(getApplicationContext(), new ArrayList(result));
            recyclerView.setAdapter(dataKulinerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
            recyclerView.setHasFixedSize(true);

            dataKulinerAdapter.setOnitemClick(data -> {
                Intent intent = new Intent(getApplicationContext(), DetailKulinerActivity.class);
                intent.putExtra("extra_nama_data_kuliner", data.getNama());
                intent.putExtra("extra_desc_data_kuliner", data.getDesc());
                intent.putExtra("extra_img_data_kuliner", data.getImg());
                intent.putExtra("extra_lat_data_kuliner", data.getLatLng().latitude);
                intent.putExtra("extra_lng_data_kuliner", data.getLatLng().longitude);
                startActivity(intent);
            });
        });
    }
}
