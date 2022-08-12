package com.example.tubesakb.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubesakb.R;
import com.example.tubesakb.view.adapter.KotaAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetFragment extends BottomSheetDialogFragment {


    ArrayList<String> listKota = new ArrayList<String>();
    KotaAdapter adapter = new KotaAdapter();
    RecyclerView rvkota;
    public BottomSheetFragment() {
        // Required empty public constructor
    }
    private OnDismisslistener ondismisslistener;
    public void setondismisslistener(OnDismisslistener ondismisslistener){
        this.ondismisslistener = ondismisslistener;
    }

    public static BottomSheetFragment newInstance(String kota) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        Bundle args = new Bundle();
        args.putString("extra_data", kota);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listKota.add("Bandung");
        listKota.add("Kab.Bandung");
        listKota.add("Kab.Bandung Barat");
        listKota.add("Cimahi");
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        String selectedkota = getArguments().getString("extra_data");

        rvkota = view.findViewById(R.id.list);
        adapter = new KotaAdapter(requireContext(),listKota,selectedkota);
        rvkota.setAdapter(adapter);
        rvkota.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvkota.setHasFixedSize(true);

        adapter.setOnitemClick(new KotaAdapter.Onclicklistener() {
            @Override
            public void onClick(String kota) {
                ondismisslistener.onDismiss(kota);
            }
        });
    }



    public interface OnDismisslistener{
        public void onDismiss(String kota);
    }
}