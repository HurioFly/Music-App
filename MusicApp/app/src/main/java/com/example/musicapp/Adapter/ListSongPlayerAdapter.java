package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ListSongPlayerAdapter extends RecyclerView.Adapter<ListSongPlayerAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> songArrayList;

    public ListSongPlayerAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_song_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtSongName.setText(song.getSongName());
        holder.txtSingerName.setText(song.getSingerName());
        Glide.with(context).load(song.getSongImage()).into(holder.imgSong);
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtSongName, txtSingerName;
        ImageView imgSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSongName = itemView.findViewById(R.id.textViewTitleSongPlayer);
            txtSingerName = itemView.findViewById(R.id.textViewSingerNamePlayer);
            imgSong = itemView.findViewById(R.id.imageViewSongPlayer);
        }
    }
}
