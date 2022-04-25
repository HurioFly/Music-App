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
import com.example.musicapp.Activity.ListGenresActivity;
import com.example.musicapp.Activity.ListSongActivity;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ListTopicAdapter extends RecyclerView.Adapter<ListTopicAdapter.ViewHolder>{
    Context context;
    ArrayList<Topic> topicArrayList;

    public ListTopicAdapter(Context context, ArrayList<Topic> topicArrayList) {
        this.context = context;
        this.topicArrayList = topicArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topicArrayList.get(position);
        Glide.with(context).load(topic.getTopicImage()).into(holder.imgTopic);
        holder.txtTopic.setText(topic.getTopicName());


    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopic;
        TextView txtTopic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopic = itemView.findViewById(R.id.imageViewTopic);
            txtTopic = itemView.findViewById(R.id.textViewTitleTopic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListGenresActivity.class);
                    intent.putExtra("topic", topicArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
