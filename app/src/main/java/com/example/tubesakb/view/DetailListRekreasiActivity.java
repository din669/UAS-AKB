package com.example.tubesakb.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubesakb.R;
import com.example.tubesakb.presenter.DaftarRekreasiImpl;
import com.example.tubesakb.view.adapter.DataRekreasiAdapter;

import java.util.ArrayList;

public class DetailListRekreasiActivity extends AppCompatActivity {

    String kota;
    RecyclerView recyclerView;
    DataRekreasiAdapter dataRekreasiAdapter;
    DaftarRekreasiImpl dataRekreasiImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        recyclerView = findViewById(R.id.list_data_rekreasi);
        kota = getIntent().getStringExtra("extra_kota");

        dataRekreasiImpl = new DaftarRekreasiImpl();
        dataRekreasiImpl.getDataRekreasi(kota);
        dataRekreasiImpl.setDataRekreasiListener(result -> {
            dataRekreasiAdapter = new DataRekreasiAdapter(getApplicationContext(), new ArrayList(result));
            recyclerView.setAdapter(dataRekreasiAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
            recyclerView.setHasFixedSize(true);

            dataRekreasiAdapter.setOnitemClick(data -> {
                Intent intent = new Intent(getApplicationContext(), DetailRekreasiActivity.class);
                intent.putExtra("extra_nama_data_rekreasi", data.getNama());
                intent.putExtra("extra_desc_data_rekreasi", data.getDesc());
                intent.putExtra("extra_img_data_rekreasi", data.getImg());
                intent.putExtra("extra_lat_data_rekreasi", data.getLatLng().latitude);
                intent.putExtra("extra_lng_data_rekreasi", data.getLatLng().longitude);
                startActivity(intent);
            });
        });
    }
}
