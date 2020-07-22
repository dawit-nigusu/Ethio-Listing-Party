package com.listeningparty.listeningparty.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.listeningparty.listeningparty.R;
import com.listeningparty.listeningparty.models.ChatPreview;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;

public class ChatsFragment extends Fragment {

    public ChatsFragment() {
        // Required empty public constructor
    }

    public static ChatsFragment newInstance() {
        return new ChatsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        RecyclerView recycler = view.findViewById(R.id.fragment_chats_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        List<ChatPreview> chatPreviews = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ChatPreview chatPreview = new ChatPreview();
            chatPreview.setUsername("Username. " + i);
            chatPreview.setLatest_message("latest message from the respective user is gonna be displayed here and most certainly is added to make this text even longer that it already is.");
            chatPreviews.add(chatPreview);
        }
        ChatsAdapter chatsAdapter = new ChatsAdapter(chatPreviews, new ChatPreviewAdapterListener() {
            @Override
            public void onProfilePictureClick(View v, int position) {
                Toast.makeText(getContext(), "Whole Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChatPreviewClick(View v, int position) {
                Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment).navigate(R.id.action_chatsFragment_to_chatFragment);
            }
        });
        recycler.setHasFixedSize(true);
        recycler.setAdapter(chatsAdapter);
        recycler.setLayoutManager(layoutManager);
        return view;
    }

}
