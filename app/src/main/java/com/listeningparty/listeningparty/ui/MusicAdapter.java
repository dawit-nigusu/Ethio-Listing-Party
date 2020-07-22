package com.listeningparty.listeningparty.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.listeningparty.listeningparty.R;
import com.listeningparty.listeningparty.models.Music;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MusicAdapter extends RecyclerView.Adapter {

    public List<Music> musicList;
    public MusicAdapterListener musicAdapterListener;

    public MusicAdapter(List<Music> musicList, MusicAdapterListener musicAdapterListener) {
        this.musicList = musicList;
        this.musicAdapterListener = musicAdapterListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_box_layout, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Music music = musicList.get(position);
        MusicViewHolder musicViewHolder = ((MusicViewHolder) holder);
        musicViewHolder.title.setText(music.title);
        musicViewHolder.subTitle.setText(music.artist);
        Glide.with(holder.itemView).load(music.getAlbum_art())
                .transition(withCrossFade()).into(musicViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        if (musicList != null) return musicList.size();
        return 0;
    }


    public class MusicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.single_box_thumb)
        ImageView thumbnail;

        @BindView(R.id.single_box_title)
        TextView title;

        @BindView(R.id.single_box_subtitle)
        TextView subTitle;

        @BindView(R.id.single_box_play)
        ImageView play;

        public MusicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            play.setOnClickListener(v -> musicAdapterListener.playButtonListener(v, getAdapterPosition()));
        }

    }

}
