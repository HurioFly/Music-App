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
import com.example.musicapp.Activity.MusicPlayerActivity;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> songArrayList;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtTitleSong.setText(song.getSongName());
        holder.txtSingerName.setText(song.getSingerName());
        Glide.with(context).load(song.getSongImage()).into(holder.imgSong);
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitleSong, txtSingerName;
        ImageView imgSong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleSong = itemView.findViewById(R.id.textViewTitleSong);
            txtSingerName = itemView.findViewById(R.id.textViewSingerName);
            imgSong = itemView.findViewById(R.id.imageViewSong);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MusicPlayerActivity.class);
                    intent.putExtra("song", songArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
