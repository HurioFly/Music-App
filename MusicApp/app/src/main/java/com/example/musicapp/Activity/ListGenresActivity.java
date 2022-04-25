package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.musicapp.Adapter.ListGenresAdapter;
import com.example.musicapp.Adapter.ListTopicAdapter;
import com.example.musicapp.Model.Genres;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListGenresActivity extends AppCompatActivity {
    Topic topic;

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageView imageViewGenres;

    ListGenresAdapter listGenresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_genres);
        DataIntent();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Genres>> callback = dataService.GetGenresByTopic(topic.getTopicID());
        callback.enqueue(new Callback<List<Genres>>() {
            @Override
            public void onResponse(Call<List<Genres>> call, Response<List<Genres>> response) {
                ArrayList<Genres> genresArrayList = (ArrayList<Genres>) response.body();
                listGenresAdapter = new ListGenresAdapter(ListGenresActivity.this, genresArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(ListGenresActivity.this, 2));
                recyclerView.setAdapter(listGenresAdapter);
            }

            @Override
            public void onFailure(Call<List<Genres>> call, Throwable t) {

            }
        });
    }

    private void init(){
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlistgenres);
        toolbar = findViewById(R.id.toolbarGenresByTopic);
        recyclerView = findViewById(R.id.recycleViewGenresByTopic);
        imageViewGenres = findViewById(R.id.imageViewGenresByTopic);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(topic.getTopicName());
        Glide.with(this).load(topic.getTopicImage()).into(imageViewGenres);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("topic")){
            topic = (Topic) intent.getSerializableExtra("topic");
        }
    }
}