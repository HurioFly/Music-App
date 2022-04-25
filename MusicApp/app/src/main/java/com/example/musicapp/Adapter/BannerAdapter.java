package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.Activity.ListSongActivity;
import com.example.musicapp.Model.Advertisement;
import com.example.musicapp.R;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder>{
    Context context;
    ArrayList<Advertisement> advertisements;
    View view;

    public BannerAdapter(Context context, ArrayList<Advertisement> advertisements) {
        this.context = context;
        this.advertisements = advertisements;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.row_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Advertisement advertisement = advertisements.get(position);
        Glide.with(context).load(advertisement.getAdvertisementImage()).into(holder.imgBackgroundBanner);
        Glide.with(context).load(advertisement.getSongImage()).into(holder.imgBanner);
        holder.txtTitleBanner.setText(advertisements.get(position).getSongName());
        holder.txtContentBanner.setText(advertisements.get(position).getAdvertisementContent());
    }

    @Override
    public int getItemCount() {
        return advertisements.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgBackgroundBanner;
        private final ImageView imgBanner;
        private final TextView txtTitleBanner;
        private final TextView txtContentBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBackgroundBanner = itemView.findViewById(R.id.imageViewBackgroundBanner);
            imgBanner = itemView.findViewById(R.id.imageViewBanner);
            txtTitleBanner = itemView.findViewById(R.id.textViewTitleBanner);
            txtContentBanner = itemView.findViewById(R.id.textViewContentBanner);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("advertisement", advertisements.get(getBindingAdapterPosition()));
                context.startActivity(intent);
            });
        }
    }
}
