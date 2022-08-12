package com.example.tubesakb.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tubesakb.R;
import com.example.tubesakb.model.DataKuliner;

import java.util.ArrayList;

public class DataKulinerAdapter extends RecyclerView.Adapter<DataKulinerAdapter.ViewHolder> {
    private ArrayList<DataKuliner> dataKuliner = new ArrayList();
    private Context context;

    public DataKulinerAdapter() {
    }

    public DataKulinerAdapter(Context context, ArrayList<DataKuliner> list) {
        this.context = context;
        this.dataKuliner = list;
    }

    private Onclicklistener onclicklistener;

    public void setOnitemClick(Onclicklistener onclicklistener) {
        this.onclicklistener = onclicklistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_data_kuliner, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataKuliner.get(position).getNama());
        holder.desc.setText(dataKuliner.get(position).getDesc());
        Glide.with(context)
                .load(dataKuliner.get(position).getImg())
                .into(holder.imageview);

        holder.itemView.setOnClickListener(v -> {
            onclicklistener.onClick(dataKuliner.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if (dataKuliner == null) {
            return 0;
        }
        return dataKuliner.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_nama_data_kuliner);
            desc = itemView.findViewById(R.id.tv_desc_data_kuliner);
            imageview = itemView.findViewById(R.id.img_data_kuliner);
        }
    }

    public interface Onclicklistener {
        public void onClick(DataKuliner data);
    }
}
