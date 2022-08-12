package com.example.tubesakb.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubesakb.R;

import java.util.List;

public class KotaAdapter extends RecyclerView.Adapter<KotaAdapter.ViewHolder>{
    private List<String> kota;
    private String selectedkota;
    private Context context;
    public KotaAdapter() {
    }
    public KotaAdapter(Context context,List<String> list,String selectedkota) {
        this.context = context;
        this.kota = list;
        this.selectedkota = selectedkota;
    }
    private Onclicklistener onclicklistener;
    public void setOnitemClick(Onclicklistener onclicklistener){
        this.onclicklistener = onclicklistener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_kota, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
    holder.title.setText(kota.get(position));
    if(kota.get(position).equalsIgnoreCase(selectedkota)){
        holder.imageview.setVisibility(View.VISIBLE);
    }else{
        holder.imageview.setVisibility(View.GONE);
    }
    holder.itemView.setOnClickListener(v -> {
        onclicklistener.onClick(kota.get(position));
    });
    }

    @Override
    public int getItemCount() {
        if (kota == null){
            return 0;
        }
        return kota.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_list_kota);
            imageview = itemView.findViewById(R.id.image_list_kota);
        }
    }

    public interface Onclicklistener{
        public void onClick(String kota);
    }
}


