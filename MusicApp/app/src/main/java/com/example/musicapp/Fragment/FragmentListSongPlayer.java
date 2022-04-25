package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.MusicPlayerActivity;
import com.example.musicapp.Adapter.ListSongPlayerAdapter;
import com.example.musicapp.R;

public class FragmentListSongPlayer extends Fragment {
    View view;
    RecyclerView recyclerView;

    public ListSongPlayerAdapter listSongPlayerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_song_player, container, false);
        recyclerView = view.findViewById(R.id.recycleViewListSongPlayer);

        if(MusicPlayerActivity.songArrayList.size() > 0){
            listSongPlayerAdapter = new ListSongPlayerAdapter(getActivity(), MusicPlayerActivity.songArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(listSongPlayerAdapter);
        }


        return view;
    }
}
