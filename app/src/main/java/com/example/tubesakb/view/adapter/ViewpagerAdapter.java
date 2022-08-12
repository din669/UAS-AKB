package com.example.tubesakb.view.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tubesakb.R;

public class ViewpagerAdapter extends PagerAdapter {
    String[] texts = {
            "Hai Kamu",
            "Jelajah Bandung",
            "Selamat Liburan"
    };
    String[] desc = {
            "Hari ini masih bingung cari tempat destinasi dibandung?",
            "Tenang aja, Jelajah Bandung bakal bantu kamu buat ngasih tau tempat rekomendasi wisata dibandung raya.",
            "Jadikan liburanmu menjadi liburan yang seru dan penuh moment bersama Jelajah Bandung. Semoga harimu menyenangkan."
    };

    int[] image = {
            R.drawable.ic_undraw_traveling,
            R.drawable.ic_undraw_destination,
            R.drawable.ic_undraw_at,
    };


    Context ctx;

    public ViewpagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.viewpager_layout, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.imageviewpager);
        TextView title = layoutScreen.findViewById(R.id.text_judul);
        TextView descs = layoutScreen.findViewById(R.id.text_desc);

        descs.setText(desc[position]);
        title.setText(texts[position]);
        imgSlide.setImageResource(image[position]);
        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
