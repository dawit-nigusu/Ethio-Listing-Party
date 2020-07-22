package com.listeningparty.listeningparty.ui;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.listeningparty.listeningparty.R;
import com.listeningparty.listeningparty.models.Music;
import com.listeningparty.listeningparty.models.MusicViewModel;
import com.scwang.wave.MultiWaveHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicPlayerFragment extends Fragment {
    String TAG = "henokg";

    @BindView(R.id.waveHeader)
    MultiWaveHeader multiWaveHeader;

    @BindView(R.id.fragment_player_exo)
    SimpleExoPlayerView player;

    @BindView(R.id.fragment_player_thumb)
    ImageView albumArtView;

    @BindView(R.id.fragment_play_artist)
    TextView artistView;

    @BindView(R.id.fragment_play_song_title)
    TextView songTitleView;

    int musicId;
    MusicViewModel musicViewModel;
    MutableLiveData<Music> music;

    OnFragmentInteractionListener mListener;

    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public static Fragment newInstance() {
        return new MusicPlayerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mListener.onMusicPlayerOpened(true);

        musicId = getArguments().getInt("musicId");
        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);
        music = musicViewModel.initMusic(musicId);
        music.observe(this, new Observer<Music>() {
            @Override
            public void onChanged(@Nullable Music music) {
                artistView.setText(music.artist);
                songTitleView.setText(music.title);
                Glide.with(MusicPlayerFragment.this).load(music.getAlbum_art())
                        .apply(RequestOptions.circleCropTransform())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                albumArtView.setImageDrawable(resource);
                                BitmapDrawable bitmapDrawable = (BitmapDrawable) albumArtView.getDrawable();
                                Palette.from(bitmapDrawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(@NonNull Palette palette) {
                                        getActivity().getWindow().setStatusBarColor(Color.WHITE);
                                        multiWaveHeader.setStartColor(palette.getVibrantColor(getResources().getColor(R.color.deep_purple_500)));
                                    }
                                });
                            }
                        });
            }
        });

        try {
            Uri src = Uri.parse("http://10.0.2.2:8080/Made with Unity Games for Mobile - Unite Berlin.mp4");
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), "listeningparty"));
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(src);

            SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
            player.setPlayer(exoPlayer);
            exoPlayer.prepare(videoSource);
            exoPlayer.setPlayWhenReady(true);
        } catch (Exception e) {
            Log.d("henokg", "onResume() returned: " + e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onMusicPlayerOpened(false);
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onMusicPlayerOpened(boolean isOpened);

    }
}
