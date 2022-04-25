package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Adapter.MusicPlayerViewPagerAdapter;
import com.example.musicapp.Fragment.FragmentListSongPlayer;
import com.example.musicapp.Fragment.FragmentMusicPlayer;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator3;

public class MusicPlayerActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager2 viewPager;
    CircleIndicator3 circleIndicator;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar seekBar;
    ImageButton imgPlay, imgBack, imgNext, imgShuffle, imgRepeat;

    public static ArrayList<Song> songArrayList = new ArrayList<>();
    public static ArrayList<Song> songArrayListTemp = new ArrayList<>();

    public static MusicPlayerViewPagerAdapter adapter;

    FragmentMusicPlayer fragmentMusicPlayer;
    FragmentListSongPlayer fragmentListSongPlayer;

    MediaPlayer mediaPlayer;

    int position = 0;
    int repeat = 0;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GetDataFromIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(fragmentMusicPlayer != null){
                    if(songArrayList.size() > 0){
                        fragmentMusicPlayer.PlayMusic(songArrayList.get(0).getSongImage());
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.ic_play_button_white);

                    if (fragmentMusicPlayer.objectAnimator != null) {
                        fragmentMusicPlayer.objectAnimator.pause();
                    }
                }
                else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.ic_pause_button_white);

                    if (fragmentMusicPlayer.objectAnimator != null) {
                        fragmentMusicPlayer.objectAnimator.resume();
                    }
                }
            }
        });

        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeat == 0){
                    repeat = 1;
                    imgRepeat.setImageResource(R.drawable.ic_repeat_purple);
                }
                else if(repeat == 1){
                    repeat = -1;
                    imgRepeat.setImageResource(R.drawable.ic_repeat_one_purple);
                }
                else {
                    repeat = 0;
                    imgRepeat.setImageResource(R.drawable.ic_repeat_white);
                }
            }
        });

        imgShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkrandom){
                    checkrandom = true;
                    imgShuffle.setImageResource(R.drawable.ic_shuffle_purple);
                    Song song = songArrayList.get(position);
                    songArrayList.remove(position);
                    Collections.shuffle(songArrayList);
                    songArrayList.add(0, song);
                    position = 0;
                }else {
                    checkrandom = false;
                    imgShuffle.setImageResource(R.drawable.ic_shuffle_white);
                    position = songArrayListTemp.indexOf(songArrayList.get(position));
                    songArrayList.removeAll(songArrayList);
                    songArrayList.addAll(songArrayListTemp);
                }
                fragmentListSongPlayer.listSongPlayerAdapter.notifyDataSetChanged();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < songArrayList.size()){
                        imgPlay.setImageResource(R.drawable.ic_pause_button_white);
                        position++;

                        if(position == songArrayList.size()){
                            position = 0;
                        }

                        fragmentMusicPlayer.PlayMusic(songArrayList.get(position).getSongImage());
                        getSupportActionBar().setTitle(songArrayList.get(position).getSongName());
                        getSupportActionBar().setSubtitle(songArrayList.get(position).getSingerName());
                        new PlayMp3().execute(songArrayList.get(position).getSongURL());
                    }
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < songArrayList.size()){
                        imgPlay.setImageResource(R.drawable.ic_pause_button_white);
                        position--;

                        if(position < 0){
                            position = songArrayList.size() - 1;
                        }

                        fragmentMusicPlayer.PlayMusic(songArrayList.get(position).getSongImage());
                        getSupportActionBar().setTitle(songArrayList.get(position).getSongName());
                        getSupportActionBar().setSubtitle(songArrayList.get(position).getSingerName());
                        new PlayMp3().execute(songArrayList.get(position).getSongURL());
                    }
                }
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songArrayList.clear();
        if(intent != null){
            if(intent.hasExtra("song")){
                Song song = intent.getParcelableExtra("song");
                songArrayList.add(song);
            }
            if(intent.hasExtra("listsong")){
                ArrayList<Song> songs = intent.getParcelableArrayListExtra("listsong");
                songArrayList = songs;
            }
        }
    }

    private void init(){
        toolbar = findViewById(R.id.toolbarMusicPlayer);
        viewPager = findViewById(R.id.viewPagerMusicPlayer);
        circleIndicator = findViewById(R.id.circleIndicatorMusicPlayer);
        txtTimeSong = findViewById(R.id.textViewTimeSong);
        txtTotalTimeSong = findViewById(R.id.textViewTotalTimeSong);
        seekBar = findViewById(R.id.seekBarSong);
        imgPlay = findViewById(R.id.imageButtonPlay);
        imgBack = findViewById(R.id.imageButtonBack);
        imgNext = findViewById(R.id.imageButtonNext);
        imgShuffle = findViewById(R.id.imageButtonShuffle);
        imgRepeat = findViewById(R.id.imageButtonRepeat);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                songArrayList.clear();
                songArrayListTemp.clear();
            }
        });

        fragmentMusicPlayer = new FragmentMusicPlayer();
        fragmentListSongPlayer = new FragmentListSongPlayer();

        adapter = new MusicPlayerViewPagerAdapter(MusicPlayerActivity.this);
        adapter.addFragment(fragmentMusicPlayer);
        adapter.addFragment(fragmentListSongPlayer);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);

        fragmentMusicPlayer = (FragmentMusicPlayer) adapter.createFragment(0);
        fragmentListSongPlayer = (FragmentListSongPlayer) adapter.createFragment(1);

        if(songArrayList.size() > 0){
            getSupportActionBar().setTitle(songArrayList.get(0).getSongName());
            getSupportActionBar().setSubtitle(songArrayList.get(0).getSingerName());

            songArrayListTemp.addAll(songArrayList);

            new PlayMp3().execute(songArrayList.get(0).getSongURL());
            imgPlay.setImageResource(R.drawable.ic_pause_button_white);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(position < songArrayList.size()){
                        imgPlay.setImageResource(R.drawable.ic_pause_button_white);
                        position++;
                        if(repeat == 0 && position == songArrayList.size()){
                            position--;
                            mediaPlayer.pause();
                            imgPlay.setImageResource(R.drawable.ic_play_button_white);

                            if (fragmentMusicPlayer.objectAnimator != null) {
                                fragmentMusicPlayer.objectAnimator.pause();
                            }
                        }
                        else {
                            if(position == songArrayList.size()){
                                position = 0;
                            }
                            if(repeat == -1){
                                if(position == 0){
                                    position = songArrayList.size();
                                }
                                position--;
                            }

                            fragmentMusicPlayer.PlayMusic(songArrayList.get(position).getSongImage());
                            getSupportActionBar().setTitle(songArrayList.get(position).getSongName());
                            getSupportActionBar().setSubtitle(songArrayList.get(position).getSingerName());
                            new PlayMp3().execute(songArrayList.get(position).getSongURL());
                        }
                    }
                    next = false;
                    handler1.removeCallbacks(this);
                }
                else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}