package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genres extends TopicAndGenres implements Serializable {
    @SerializedName("genresID")
    @Expose
    private String genresID;
    @SerializedName("topicID")
    @Expose
    private String topicID;
    @SerializedName("genresName")
    @Expose
    private String genresName;
    @SerializedName("genresImage")
    @Expose
    private String genresImage;

    public String getGenresID() {
        return genresID;
    }

    public void setGenresID(String genresID) {
        this.genresID = genresID;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getGenresName() {
        return genresName;
    }

    public void setGenresName(String genresName) {
        this.genresName = genresName;
    }

    public String getGenresImage() {
        return genresImage;
    }

    public void setGenresImage(String genresImage) {
        this.genresImage = genresImage;
    }
}