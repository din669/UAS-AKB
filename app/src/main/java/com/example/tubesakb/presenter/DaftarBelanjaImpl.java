package com.example.tubesakb.presenter;

import android.util.Log;

import com.example.tubesakb.model.DataBelanja;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaftarBelanjaImpl implements DataBelanjaInterfaces {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data_belanja");

    private DataBelanjaListener dataBelanjaListener;

    public void setDataBelanjaListener(DataBelanjaListener listener) {
        this.dataBelanjaListener = listener;
    }

    @Override
    public void getDataBelanja(String kota) {
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
                Log.d("TAG JSON", "getDataBelanja: " + res);

                ArrayList<DataBelanja> result = new ArrayList<>();
                for (int i = 0; i < res.size(); i++) {
                    HashMap<String, Object> explrObject = (HashMap<String, Object>) res.get(i);
                    Log.d("TAG JSON SATUAN", "getDataBelanja: " + explrObject);

                    String namaBelanja = (String) explrObject.get("nama_belanja");
                    String desc = (String) explrObject.get("deskripsi");
                    String img = (String) explrObject.get("gambar");
                    String lat = String.valueOf(explrObject.get("latitude"));
                    String lang = String.valueOf(explrObject.get("longitude"));

                    DataBelanja dataBelanja = new DataBelanja(namaBelanja, desc, img, new LatLng(Double.parseDouble(lat), Double.parseDouble(lang)));
                    result.add(dataBelanja);
                }
                dataBelanjaListener.onSuccess(result);
            }
        });
    }

    public interface DataBelanjaListener {
        void onSuccess(List<DataBelanja> result);
    }
}
