package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.ListTopicActivity;
import com.example.musicapp.Adapter.TopicAndGenresAdapter;
import com.example.musicapp.Model.Genres;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.Model.TopicAndGenresList;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTopicAndGenres extends Fragment {
    View view;
    RecyclerView recyclerViewTopicAndGenres;
    TextView txtMoreTopicAndGenres;
    TopicAndGenresAdapter topicAndGenresAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic_and_genres, container, false);
        txtMoreTopicAndGenres = view.findViewById(R.id.textViewMoreTopicAndGenres);
        recyclerViewTopicAndGenres = view.findViewById(R.id.recycleViewTopicAndGenres);
        txtMoreTopicAndGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListTopicActivity.class);
                startActivity(intent);
            }
        });

        GetData();

        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<TopicAndGenresList> callback = dataService.GetTopicAndGenresForCurrentDay();
        callback.enqueue(new Callback<TopicAndGenresList>() {
            @Override
            public void onResponse(@NonNull Call<TopicAndGenresList> call, @NonNull Response<TopicAndGenresList> response) {
                ArrayList<Topic> topicArrayList = (ArrayList<Topic>) response.body().getTopic();
                ArrayList<Genres> genresArrayList = (ArrayList<Genres>) response.body().getGenres();

                topicAndGenresAdapter = new TopicAndGenresAdapter(getActivity(), topicArrayList, genresArrayList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

                recyclerViewTopicAndGenres.setLayoutManager(linearLayoutManager);
                recyclerViewTopicAndGenres.setAdapter(topicAndGenresAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<TopicAndGenresList> call, @NonNull Throwable t) {

            }
        });
    }
}
