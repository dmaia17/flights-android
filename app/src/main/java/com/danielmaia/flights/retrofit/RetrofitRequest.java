package com.danielmaia.flights.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static RetrofitRequest INSTANCE;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://vcugj6hmt5.execute-api.us-east-1.amazonaws.com";

    public static RetrofitRequest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitRequest();
        }
        return INSTANCE;
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
