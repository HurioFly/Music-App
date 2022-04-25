//package com.example.musicapp.Adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.musicapp.Activity.ListSongActivity;
//import com.example.musicapp.Activity.MusicPlayerActivity;
//import com.example.musicapp.Model.Playlist;
//import com.example.musicapp.Model.Song;
//import com.example.musicapp.R;
//
//import java.util.ArrayList;
//
//public class ListTopSongAdapter extends RecyclerView.Adapter<ListTopSongAdapter.ViewHolder>{
//    Context context;
//    ArrayList<Song> songArrayList;
//
//    public ListTopSongAdapter(Context context, ArrayList<Song> songArrayList) {
//        this.context = context;
//        this.songArrayList = songArrayList;
//    }
//
//    @NonNull
//    @Override
//    public ListTopSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.row_top_song, parent, false);
//        return new ListTopSongAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ListTopSongAdapter.ViewHolder holder, int position) {
//        Song song = songArrayList.get(position);
//
//        Glide.with(context).load(song.getPlaylistImage()).into(holder.imgPlaylist);
//        holder.txtPlaylistName.setText(playlist.getPlaylistName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return playlistArrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView txtTitleSong, txtSingerName;
//        ImageView imgSong;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtTitleSong = itemView.findViewById(R.id.textViewTitleSong);
//            txtSingerName = itemView.findViewById(R.id.textViewSingerName);
//            imgSong = itemView.findViewById(R.id.imageViewSong);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, MusicPlayerActivity.class);
//                    intent.putExtra("song", songArrayList.get(getBindingAdapterPosition()));
//                    context.startActivity(intent);
//                }
//            });
//        }
//    }
//}
