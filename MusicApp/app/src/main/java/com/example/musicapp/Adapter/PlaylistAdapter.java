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
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHoler> {
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public PlaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public PlaylistAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_playlist, parent, false);
        return new PlaylistAdapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHoler holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Glide.with(context).load(playlist.getPlaylistImage()).into(holder.imgImagePlaylist);
        holder.txtPlaylistName.setText(playlistArrayList.get(position).getPlaylistName());
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView imgImagePlaylist;
        TextView txtPlaylistName;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imgImagePlaylist = itemView.findViewById(R.id.imageViewPlaylist);
            txtPlaylistName = itemView.findViewById(R.id.textViewPlaylistName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("playlist", playlistArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
