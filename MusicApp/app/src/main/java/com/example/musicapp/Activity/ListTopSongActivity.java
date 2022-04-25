package com.example.musicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.musicapp.Adapter.ListAlbumAdapter;
import com.example.musicapp.Adapter.ListPlaylistAdapter;
import com.example.musicapp.Adapter.TopSongAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTopSongActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Menu menu;

    TopSongAdapter topSongAdapter;

    ArrayList<Song> songArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_top_song);
        init();
        GetDaTa();
    }

    private void GetDaTa() {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetAllTopSong();

        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                topSongAdapter = new TopSongAdapter(ListTopSongActivity.this, songArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListTopSongActivity.this));
                recyclerView.setAdapter(topSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void init(){
        toolbar = findViewById(R.id.toolbarListTopSong);
        recyclerView = findViewById(R.id.recycleViewListTopSong);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_song, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ListTopSongActivity.this, MusicPlayerActivity.class);
        intent.putExtra("listsong", songArrayList);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}