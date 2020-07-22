package com.listeningparty.listeningparty.models;

import com.listeningparty.listeningparty.network.ApiClient;
import io.objectbox.annotation.Id;

public class Music {

    @Id
    public int objectBoxId;
    public int id;
    public String title;
    public String artist;
    private String album_art;
    private String file;

    public String getAlbum_art() {
        return ApiClient.MEDIA_BASE_URL + album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }
}
