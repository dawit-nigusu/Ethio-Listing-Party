package com.listeningparty.listeningparty.models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.listeningparty.listeningparty.repository.MusicRepository;

import java.util.List;

public class MusicViewModel extends ViewModel {

    private MusicRepository musicRepository;

    public MutableLiveData<List<Music>> initAllMusic() {
        initMusicRepository();
        return musicRepository.getAllMusic();
    }

    public MutableLiveData<Music> initMusic(int musicId) {
        initMusicRepository();
        return musicRepository.getMusic(musicId);
    }

    private void initMusicRepository() {
        if (this.musicRepository == null) {
            musicRepository = new MusicRepository();
        }
    }

}
