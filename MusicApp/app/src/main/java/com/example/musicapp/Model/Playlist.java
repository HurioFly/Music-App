package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("playlistID")
    @Expose
    private String playlistID;
    @SerializedName("playlistName")
    @Expose
    private String playlistName;
    @SerializedName("playlistImage")
    @Expose
    private String playlistImage;
    @SerializedName("playlistBackground")
    @Expose
    private String playlistBackground;

    public String getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistImage() {
        return playlistImage;
    }

    public void setPlaylistImage(String playlistImage) {
        this.playlistImage = playlistImage;
    }

    public String getPlaylistBackground() {
        return playlistBackground;
    }

    public void setPlaylistBackground(String playlistBackground) {
        this.playlistBackground = playlistBackground;
    }

}