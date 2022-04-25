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
import com.example.musicapp.R;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHoler> {
    Context context;
    ArrayList<Album> albums;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_album, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Album album = albums.get(position);
        Glide.with(context).load(album.getAlbumImage()).into(holder.imgImageAlbum);
        holder.txtTitleAlbum.setText(albums.get(position).getAlbumName());
        holder.txtSingerName.setText(albums.get(position).getSingerName());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView imgImageAlbum;
        TextView txtTitleAlbum, txtSingerName;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imgImageAlbum = itemView.findViewById(R.id.imageViewAlbum);
            txtTitleAlbum = itemView.findViewById(R.id.textViewTitleAlbum);
            txtSingerName = itemView.findViewById(R.id.textViewSingerName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album", albums.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
