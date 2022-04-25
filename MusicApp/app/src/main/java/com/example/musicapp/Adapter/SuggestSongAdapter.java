package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.Activity.MusicPlayerActivity;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestSongAdapter extends RecyclerView.Adapter<SuggestSongAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songArrayList;

    public SuggestSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_suggest_song, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleSong, txtSingerName;
        ImageView imgSong, imgLoveSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleSong = itemView.findViewById(R.id.textViewTitleSong);
            txtSingerName = itemView.findViewById(R.id.textViewSingerName);
            imgSong = itemView.findViewById(R.id.imageViewSuggestSong);
            imgLoveSong = itemView.findViewById(R.id.imageViewLoveSong);

            imgLoveSong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLoveSong.setSelected(!imgLoveSong.isSelected());

                    String like = "-1";
                    if(imgLoveSong.isSelected()){
                        like = "1";
                    }

                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLikes(songArrayList.get(getBindingAdapterPosition()).getSongID(), like);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
//                            String resuilt = response.body();
//                            if(resuilt.equals("Success")){
//                                Toast.makeText(context,"Thành công",Toast.LENGTH_SHORT).show();
//                            }else
//                            {
//                                Toast.makeText(context,"Lỗi",Toast.LENGTH_SHORT).show();
//                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });

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
