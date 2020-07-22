package com.listeningparty.listeningparty.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String API_BASE_URL = "http://10.0.2.2:3000";
    public static final String MEDIA_BASE_URL = "http://10.0.2.2:8080";
//    public static final String API_BASE_URL = "http://192.168.43.20:3000";
//    public static final String MEDIA_BASE_URL = "http://192.168.43.20:8080";


    public static Retrofit retrofit;

    public static Retrofit getInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

}
