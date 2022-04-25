package com.example.musicapp.Service;

import com.example.musicapp.Model.Advertisement;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Genres;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Song;
import com.example.musicapp.Model.Topic;
import com.example.musicapp.Model.TopicAndGenresList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("advertisement.php")
    Call<List<Advertisement>> GetAdvertisement();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("topicandgenresforcurrentday.php")
    Call<TopicAndGenresList> GetTopicAndGenresForCurrentDay();

    @GET("topsong.php")
    Call<List<Song>> GetTopSong();

    @GET("alltopsong.php")
    Call<List<Song>> GetAllTopSong();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistForCurrentDay();

    @GET("suggestsong.php")
    Call<List<Song>> GetSuggestSong();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongInBanner(@Field("advertisementID") String advertisementID);

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongInPlaylist(@Field("playlistID") String playlistID);

    @GET("allplaylist.php")
    Call<List<Playlist>> GetAllPlaylist();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongInGenres(@Field("genresID") String genresID);

    @GET("alltopic.php")
    Call<List<Topic>> GetAllTopic();

    @FormUrlEncoded
    @POST("genresbytopic.php")
    Call<List<Genres>> GetGenresByTopic(@Field("topicID") String topicID);

    @GET("allalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongInAlbum(@Field("albumID") String albumID);

    @FormUrlEncoded
    @POST("updatelikes.php")
    Call<String>UpdateLikes(@Field("songID") String songID, @Field("like") String like);
}
