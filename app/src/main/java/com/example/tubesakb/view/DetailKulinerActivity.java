package com.example.tubesakb.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.tubesakb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailKulinerActivity extends AppCompatActivity implements OnMapReadyCallback {

    String judul;
    String deskripsi;
    String img;
    double lat;
    double lng;
    LatLng latLng;

    TextView title;
    TextView desc;
    ImageView image;

    GoogleMap maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);
        title = findViewById(R.id.tv_nama_data_kuliner);
        desc = findViewById(R.id.tv_desc_data_kuliner);
        image = findViewById(R.id.img_data_kuliner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });


        judul = getIntent().getStringExtra("extra_nama_data_kuliner");
        deskripsi = getIntent().getStringExtra("extra_desc_data_kuliner");
        img = getIntent().getStringExtra("extra_img_data_kuliner");
        lat = getIntent().getDoubleExtra("extra_lat_data_kuliner",0);
        lng = getIntent().getDoubleExtra("extra_lng_data_kuliner",0);
        latLng = new LatLng(lat,lng);

        title.setText(judul);
        desc.setText(deskripsi);
        Glide.with(getApplicationContext())
                .load(img)
                .into(image);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_kuliner);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        maps = googleMap;

        maps.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .title(judul)
        );
        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f));
    }
}
