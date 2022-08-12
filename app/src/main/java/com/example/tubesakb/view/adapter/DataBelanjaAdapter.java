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
import com.example.tubesakb.model.DataBelanja;

import java.util.ArrayList;

public class DataBelanjaAdapter extends RecyclerView.Adapter<DataBelanjaAdapter.ViewHolder> {
    private ArrayList<DataBelanja> dataBelanja = new ArrayList();
    private Context context;

    public DataBelanjaAdapter() {
    }

    public DataBelanjaAdapter(Context context, ArrayList<DataBelanja> list) {
        this.context = context;
        this.dataBelanja = list;
    }

    private Onclicklistener onclicklistener;

    public void setOnitemClick(Onclicklistener onclicklistener) {
        this.onclicklistener = onclicklistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_data_belanja, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataBelanja.get(position).getNama());
        holder.desc.setText(dataBelanja.get(position).getDesc());
        Glide.with(context)
                .load(dataBelanja.get(position).getImg())
                .into(holder.imageview);

        holder.itemView.setOnClickListener(v -> {
            onclicklistener.onClick(dataBelanja.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if (dataBelanja == null) {
            return 0;
        }
        return dataBelanja.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_nama_data_belanja);
            desc = itemView.findViewById(R.id.tv_desc_data_belanja);
            imageview = itemView.findViewById(R.id.img_data_belanja);
        }
    }

    public interface Onclicklistener {
        public void onClick(DataBelanja data);
    }
}
