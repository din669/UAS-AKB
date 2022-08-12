package com.example.tubesakb.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubesakb.R;
import com.example.tubesakb.presenter.DaftarBelanjaImpl;
import com.example.tubesakb.view.adapter.DataBelanjaAdapter;

import java.util.ArrayList;

public class DetailListBelanjaActivity extends AppCompatActivity {

    String kota;
    RecyclerView recyclerView;
    DataBelanjaAdapter dataBelanjaAdapter;
    DaftarBelanjaImpl dataBelanjaImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        recyclerView = findViewById(R.id.list_data_belanja);
        kota = getIntent().getStringExtra("extra_kota");

        dataBelanjaImpl = new DaftarBelanjaImpl();
        dataBelanjaImpl.getDataBelanja(kota);
        dataBelanjaImpl.setDataBelanjaListener(result -> {
            dataBelanjaAdapter = new DataBelanjaAdapter(getApplicationContext(), new ArrayList(result));
            recyclerView.setAdapter(dataBelanjaAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
            recyclerView.setHasFixedSize(true);

            dataBelanjaAdapter.setOnitemClick(data -> {
                Intent intent = new Intent(getApplicationContext(), DetailBelanjaActivity.class);
                intent.putExtra("extra_nama_data_belanja", data.getNama());
                intent.putExtra("extra_desc_data_belanja", data.getDesc());
                intent.putExtra("extra_img_data_belanja", data.getImg());
                intent.putExtra("extra_lat_data_belanja", data.getLatLng().latitude);
                intent.putExtra("extra_lng_data_belanja", data.getLatLng().longitude);
                startActivity(intent);
            });
        });
    }

}
