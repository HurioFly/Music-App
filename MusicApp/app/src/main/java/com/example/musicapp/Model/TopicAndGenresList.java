package com.example.musicapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicAndGenresList {
    @SerializedName("Genres")
    @Expose
    private List<Genres> genres = null;
    @SerializedName("Topic")
    @Expose
    private List<Topic> topic = null;

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }
}