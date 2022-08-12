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
import com.example.tubesakb.model.DataAlam;

import java.util.ArrayList;

public class DataAlamAdapter extends RecyclerView.Adapter<DataAlamAdapter.ViewHolder> {
    private ArrayList<DataAlam> dataAlam = new ArrayList();
    private Context context;

    public DataAlamAdapter() {
    }

    public DataAlamAdapter(Context context, ArrayList<DataAlam> list) {
        this.context = context;
        this.dataAlam = list;
    }

    private Onclicklistener onclicklistener;

    public void setOnitemClick(Onclicklistener onclicklistener) {
        this.onclicklistener = onclicklistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_data_alam, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataAlam.get(position).getNama());
        holder.desc.setText(dataAlam.get(position).getDesc());
        Glide.with(context)
                .load(dataAlam.get(position).getImg())
                .into(holder.imageview);

        holder.itemView.setOnClickListener(v -> {
            onclicklistener.onClick(dataAlam.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if (dataAlam == null) {
            return 0;
        }
        return dataAlam.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_nama_data_alam);
            desc = itemView.findViewById(R.id.tv_desc_data_alam);
            imageview = itemView.findViewById(R.id.img_data_alam);
        }
    }

    public interface Onclicklistener {
        public void onClick(DataAlam data);
    }
}
