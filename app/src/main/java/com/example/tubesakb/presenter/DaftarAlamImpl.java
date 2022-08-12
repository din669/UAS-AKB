package com.example.tubesakb.presenter;

import android.util.Log;

import com.example.tubesakb.model.DataAlam;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaftarAlamImpl implements DataAlamInterfaces {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data_alam");

    private DataAlamListener dataAlamListener;

    public void setDataAlamListener(DataAlamListener listener) {
        this.dataAlamListener = listener;
    }

    @Override
    public void getDataAlam(String kota) {
        String kota_kecil;
        if (kota.equalsIgnoreCase("Bandung")) {
            kota_kecil = "kota_bandung";
        } else if (kota.equalsIgnoreCase("Kab.Bandung")) {
            kota_kecil = "kab_bandung";
        } else if (kota.equalsIgnoreCase("Kab.Bandung barat")) {
            kota_kecil = "kab_bandung_barat";
        } else if (kota.equalsIgnoreCase("Cimahi")) {
            kota_kecil = "cimahi";
        } else {
            kota_kecil = "";
        }
        myRef.child(kota_kecil).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                ArrayList<Object> res = (ArrayList<Object>) task.getResult().getValue();
                Log.d("TAG JSON", "getDataAlam: " + res);

                ArrayList<DataAlam> result = new ArrayList<DataAlam>();
                for (int i = 0; i < res.size(); i++) {
                    HashMap<String, Object> explrObject = (HashMap<String, Object>) res.get(i);
                    Log.d("TAG JSON SATUAN", "getDataAlam: " + explrObject);

                    String namaWisata = (String) explrObject.get("nama_wisata");
                    String desc = (String) explrObject.get("deskripsi");
                    String img = (String) explrObject.get("gambar");
                    String lat = String.valueOf(explrObject.get("latitude"));
                    String lang = String.valueOf(explrObject.get("longitude"));

                    DataAlam dataAlam = new DataAlam(namaWisata, desc, img, new LatLng(Double.parseDouble(lat), Double.parseDouble(lang)));
                    result.add(dataAlam);
                }
                dataAlamListener.onSuccess(result);
            }
        });
    }

    public interface DataAlamListener {
        void onSuccess(List<DataAlam> result);
    }
}

