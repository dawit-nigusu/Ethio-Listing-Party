package com.listeningparty.listeningparty.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.listeningparty.listeningparty.models.Music;
import com.listeningparty.listeningparty.network.ApiClient;
import com.listeningparty.listeningparty.network.ListeningPartyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicRepository {

    private ListeningPartyService listeningPartyService;

    public MusicRepository() {
        listeningPartyService = ApiClient.getInstance().create(ListeningPartyService.class);
    }

    public MutableLiveData<List<Music>> getAllMusic() {
        final MutableLiveData<List<Music>> allMusic = new MutableLiveData<>();
        listeningPartyService.getAllMusic().enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                allMusic.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("henokg", t.getMessage());
            }
        });
        return allMusic;
    }

    public MutableLiveData<Music> getMusic(int musicId) {
        final MutableLiveData<Music> music = new MutableLiveData<>();
        listeningPartyService.getMusic(musicId).enqueue(new Callback<Music>() {
            @Override
            public void onResponse(Call<Music> call, Response<Music> response) {
                music.postValue(response.body());
                Log.d("henokg", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<Music> call, Throwable t) {
                Log.d("henokg", t.getMessage());
            }
        });

        return music;
    }

}
