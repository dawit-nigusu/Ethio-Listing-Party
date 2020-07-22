package com.listeningparty.listeningparty.models;

import com.listeningparty.listeningparty.network.ApiClient;

public class ChatPreview {

    int id;
    String latest_message;
    String username;
    String user_profile_picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatest_message() {
        return latest_message;
    }

    public void setLatest_message(String latest_message) {
        this.latest_message = latest_message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_profile_picture() {
        return ApiClient.MEDIA_BASE_URL + user_profile_picture;
    }

    public void setUser_profile_picture(String user_profile_picture) {
        this.user_profile_picture = user_profile_picture;
    }
}
