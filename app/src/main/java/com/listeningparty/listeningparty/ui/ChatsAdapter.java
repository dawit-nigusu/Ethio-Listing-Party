package com.listeningparty.listeningparty.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.listeningparty.listeningparty.R;
import com.listeningparty.listeningparty.models.ChatPreview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ChatsAdapter extends RecyclerView.Adapter {

    public List<ChatPreview> chatPreviews;
    public ChatPreviewAdapterListener chatPreviewAdapterListener;

    public ChatsAdapter(List<ChatPreview> chatPreviews, ChatPreviewAdapterListener chatPreviewAdapterListener) {
        this.chatPreviews = chatPreviews;
        this.chatPreviewAdapterListener = chatPreviewAdapterListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_chat_preview, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatPreview chatPreview = chatPreviews.get(position);
        ChatViewHolder chatViewHolder = ((ChatViewHolder) holder);
        chatViewHolder.username.setText(chatPreview.getUsername());
        chatViewHolder.lastMessage.setText(chatPreview.getLatest_message());
//        Glide.with(holder.itemView).load(chatPreview.getUser_profile_picture())
//                .transition(withCrossFade()).into(chatViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        if (chatPreviews != null) return chatPreviews.size();
        return 0;
    }


    public class ChatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.single_chat_thumb)
        ImageView thumbnail;

        @BindView(R.id.single_chat_username)
        TextView username;

        @BindView(R.id.single_chat_last_message)
        TextView lastMessage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> chatPreviewAdapterListener.onChatPreviewClick(v, getAdapterPosition()));
            thumbnail.setOnClickListener(v -> chatPreviewAdapterListener.onProfilePictureClick(v, getAdapterPosition()));
        }

    }

}
