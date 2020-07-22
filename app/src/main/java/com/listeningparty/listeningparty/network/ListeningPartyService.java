package com.listeningparty.listeningparty.network;

import com.listeningparty.listeningparty.models.Music;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListeningPartyService {

    @GET("/music")
    Call<List<Music>> getAllMusic();

    @GET("/music/{id}")
    Call<Music> getMusic(@Path("id") int musicId);

}
