package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.Activity.ListGenresActivity;
import com.example.musicapp.Activity.ListSongActivity;
import com.example.musicapp.Model.Genres;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.Model.TopicAndGenres;
import com.example.musicapp.R;

import java.util.ArrayList;

public class TopicAndGenresAdapter extends RecyclerView.Adapter<TopicAndGenresAdapter.ViewHolder> {
    Context context;
    View view;

    ArrayList<TopicAndGenres> topicAndGenresArrayList = new ArrayList<>();

    public TopicAndGenresAdapter(Context context, ArrayList<Topic> topicArrayList, ArrayList<Genres> genresArrayList) {
        this.context = context;

        this.topicAndGenresArrayList.addAll(topicArrayList);
        this.topicAndGenresArrayList.addAll(genresArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.row_topic_and_genres, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(topicAndGenresArrayList.get(position).getClass() == Topic.class){
            Topic topic = (Topic) topicAndGenresArrayList.get(position);
            Glide.with(context).load(topic.getTopicImage()).into(holder.imgTopicAndGenres);
            holder.txtTitleTopicAndGenres.setText(topic.getTopicName());

            view.setOnClickListener(view -> {
                Intent intent = new Intent(context, ListGenresActivity.class);
                intent.putExtra("topic", topic);
                context.startActivity(intent);
            });
        }
        else {
            Genres genres = (Genres) topicAndGenresArrayList.get(position);
            Glide.with(context).load(genres.getGenresImage()).into(holder.imgTopicAndGenres);
            holder.txtTitleTopicAndGenres.setText(genres.getGenresName());

            view.setOnClickListener(view -> {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("genres", genres);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return topicAndGenresArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopicAndGenres;
        TextView txtTitleTopicAndGenres;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopicAndGenres = itemView.findViewById(R.id.imageViewTopicAndGenres);
            txtTitleTopicAndGenres = itemView.findViewById(R.id.textViewTitleTopicAndGenres);
        }
    }
}
