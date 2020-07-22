package com.listeningparty.listeningparty.ui;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.listeningparty.listeningparty.R;
import com.listeningparty.listeningparty.models.Music;
import com.listeningparty.listeningparty.models.MusicViewModel;
import com.listeningparty.listeningparty.repository.MusicRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    @BindView(R.id.fragment_feed_recycler)
    RecyclerView recyclerView;

    MusicViewModel musicViewModel;
    MutableLiveData<List<Music>> musicList;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);
        musicList = musicViewModel.initAllMusic();
        musicList.observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(@Nullable final List<Music> musicList) {
                for (Music music :
                        musicList) {
                    Log.d("henokg", "onChanged() returned: " + music.getAlbum_art());
                }
                MusicAdapter musicAdapter = new MusicAdapter(musicList, (v, position) -> {
                    Bundle bundle = new Bundle();
                    bundle.putInt("musicId", musicList.get(position).id);
                    Navigation.findNavController(v).navigate(R.id.action_feedFragment_to_musicPlayerFragment, bundle);
                });
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(musicAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }

}
