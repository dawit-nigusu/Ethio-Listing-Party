package com.listeningparty.listeningparty.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.listeningparty.listeningparty.R;

public class ChatFragment extends Fragment {

    FromChatFragmentToMainActivityInteractionListener mListener;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener.onChatOpened(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FromChatFragmentToMainActivityInteractionListener) {
            mListener = (FromChatFragmentToMainActivityInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FromChatFragmentToMainActivityInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onChatOpened(false);
        mListener = null;
    }

    public interface FromChatFragmentToMainActivityInteractionListener {

        void onChatOpened(boolean isOpened);

    }

}
