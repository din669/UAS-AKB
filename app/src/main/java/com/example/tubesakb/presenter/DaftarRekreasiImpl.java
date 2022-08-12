package com.example.tubesakb.presenter;

import android.util.Log;

import com.example.tubesakb.model.DataRekreasi;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaftarRekreasiImpl implements DataRekreasiInterfaces {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data_rekreasi");

    private DataRekreasiListener dataRekreasiListener;

    public void setDataRekreasiListener(DataRekreasiListener listener) {
        this.dataRekreasiListener = listener;
    }

    @Override
    public void getDataRekreasi(String kota) {
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
                Log.d("TAG JSON", "getDataRekreasi: " + res);

                ArrayList<DataRekreasi> result = new ArrayList<>();
                for (int i = 0; i < res.size(); i++) {
                    HashMap<String, Object> explrObject = (HashMap<String, Object>) res.get(i);
                    Log.d("TAG JSON SATUAN", "getDataRekreasi: " + explrObject);

                    String namaRekreasi = (String) explrObject.get("nama_rekreasi");
                    String desc = (String) explrObject.get("deskripsi");
                    String img = (String) explrObject.get("gambar");
                    String lat = String.valueOf(explrObject.get("latitude"));
                    String lang = String.valueOf(explrObject.get("longitude"));

                    DataRekreasi dataRekreasi = new DataRekreasi(namaRekreasi, desc, img, new LatLng(Double.parseDouble(lat), Double.parseDouble(lang)));
                    result.add(dataRekreasi);
                }
                dataRekreasiListener.onSuccess(result);
            }
        });
    }

    public interface DataRekreasiListener {
        void onSuccess(List<DataRekreasi> result);
    }
}
