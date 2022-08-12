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
import com.example.tubesakb.model.DataRekreasi;

import java.util.ArrayList;

public class DataRekreasiAdapter extends RecyclerView.Adapter<DataRekreasiAdapter.ViewHolder> {
    private ArrayList<DataRekreasi> dataRekreasi = new ArrayList();
    private Context context;

    public DataRekreasiAdapter() {
    }

    public DataRekreasiAdapter(Context context, ArrayList<DataRekreasi> list) {
        this.context = context;
        this.dataRekreasi = list;
    }

    private Onclicklistener onclicklistener;

    public void setOnitemClick(Onclicklistener onclicklistener) {
        this.onclicklistener = onclicklistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_data_rekreasi, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataRekreasi.get(position).getNama());
        holder.desc.setText(dataRekreasi.get(position).getDesc());
        Glide.with(context)
                .load(dataRekreasi.get(position).getImg())
                .into(holder.imageview);

        holder.itemView.setOnClickListener(v -> {
            onclicklistener.onClick(dataRekreasi.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if (dataRekreasi == null) {
            return 0;
        }
        return dataRekreasi.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_nama_data_rekreasi);
            desc = itemView.findViewById(R.id.tv_desc_data_rekreasi);
            imageview = itemView.findViewById(R.id.img_data_rekreasi);
        }
    }

    public interface Onclicklistener {
        public void onClick(DataRekreasi data);
    }
}
