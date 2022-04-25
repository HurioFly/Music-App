package com.example.musicapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Parcelable {
    @SerializedName("songID")
    @Expose
    private String songID;
    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("songImage")
    @Expose
    private String songImage;
    @SerializedName("singerName")
    @Expose
    private String singerName;
    @SerializedName("songURL")
    @Expose
    private String songURL;
    @SerializedName("likes")
    @Expose
    private String likes;

    protected Song(Parcel in) {
        songID = in.readString();
        songName = in.readString();
        songImage = in.readString();
        singerName = in.readString();
        songURL = in.readString();
        likes = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(songID);
        parcel.writeString(songName);
        parcel.writeString(songImage);
        parcel.writeString(singerName);
        parcel.writeString(songURL);
        parcel.writeString(likes);
    }
}