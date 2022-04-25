package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.musicapp.Adapter.ListSongAdapter;
import com.example.musicapp.Model.Advertisement;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Genres;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewlistsong;
    TextView textViewButtonPlay;
    ImageView imageViewSongInBanner;

    ArrayList<Song> songArrayList;

    ListSongAdapter listSongAdapter;

    Advertisement advertisement;
    Playlist playlist;
    Genres genres;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataIntent();
        mapping();
        init();
        if(advertisement !=null && !advertisement.getSongName().equals("")){
            setValueInView(advertisement.getSongName(), advertisement.getSongImage());
            getDataAdvertisement(advertisement.getAdvertisementID());
        }
        if(playlist !=null && !playlist.getPlaylistName().equals("")){
            setValueInView(playlist.getPlaylistName(), playlist.getPlaylistImage());
            getDataPlaylist(playlist.getPlaylistID());
        }
        if(genres !=null && !genres.getGenresName().equals("")){
            setValueInView(genres.getGenresName(),genres.getGenresImage());
            getDataGenres(genres.getGenresID());
        }
        if(album !=null && !album.getAlbumName().equals("")){
            setValueInView(album.getAlbumName(),album.getAlbumImage());
            getDataAlbum(album.getAlbumID());
        }
    }

    private void eventClick(){
        textViewButtonPlay.setEnabled(true);
        textViewButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSongActivity.this, MusicPlayerActivity.class);
                intent.putExtra("listsong", songArrayList);
                startActivity(intent);
            }
        });
    }

    private void getDataAlbum(String idalbum) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetListSongInAlbum(idalbum);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songArrayList);
                recyclerViewlistsong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewlistsong.setAdapter(listSongAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataGenres(String genresID) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetListSongInGenres(genresID);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songArrayList);
                recyclerViewlistsong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewlistsong.setAdapter(listSongAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String playlistID) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetListSongInPlaylist(playlistID);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songArrayList);
                recyclerViewlistsong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewlistsong.setAdapter(listSongAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataAdvertisement(String advertisementID) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetListSongInBanner(advertisementID);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this, songArrayList);
                recyclerViewlistsong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewlistsong.setAdapter(listSongAdapter);
                eventClick();
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String name, String image) {
        collapsingToolbarLayout.setTitle(name);
//        try {
//            URL url = new URL(hinhbaihat);
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
//            collapsingToolbarLayout.setBackground(bitmapDrawable);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Glide.with(this).load(image).into(imageViewSongInBanner);
    }

    private void init(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(view -> finish());
        textViewButtonPlay.setEnabled(false);
    }

    private void mapping() {
        coordinatorLayout = findViewById(R.id.coordinatorlayoutlistsong);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlistsong);
        toolbar = findViewById(R.id.toolbarlistsong);
        recyclerViewlistsong = findViewById(R.id.recycleviewlistsong);
        textViewButtonPlay = findViewById(R.id.textViewButtonPlay);
        imageViewSongInBanner = findViewById(R.id.imageviewbannersong);
    }

    private void DataIntent() {

        Intent intent = getIntent();
        if(intent !=null){
            if(intent.hasExtra("advertisement")){
                advertisement = (Advertisement) intent.getSerializableExtra("advertisement");
            }
            if(intent.hasExtra("playlist")){
                playlist = (Playlist) intent.getSerializableExtra("playlist");
            }
            if(intent.hasExtra("genres")){
                genres = (Genres) intent.getSerializableExtra("genres");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }

    }
}