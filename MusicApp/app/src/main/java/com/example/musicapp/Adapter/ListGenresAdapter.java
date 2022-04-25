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
import com.example.musicapp.Model.Genres;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ListGenresAdapter extends RecyclerView.Adapter<ListGenresAdapter.ViewHolder>{
    Context context;
    ArrayList<Genres> genresArrayList;

    public ListGenresAdapter(Context context, ArrayList<Genres> genresArrayList) {
        this.context = context;
        this.genresArrayList = genresArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_genres, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genres genres = genresArrayList.get(position);
        Glide.with(context).load(genres.getGenresImage()).into(holder.imgGenres);
        holder.txtGenres.setText(genres.getGenresName());
    }

    @Override
    public int getItemCount() {
        return genresArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgGenres;
        TextView txtGenres;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGenres = itemView.findViewById(R.id.imageViewGenres);
            txtGenres = itemView.findViewById(R.id.textViewTitleGenres);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("genres", genresArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
