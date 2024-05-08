package com.example.finalproject.api.api_login;

import com.example.finalproject.module.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APILogin {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    APILogin api = new Retrofit.Builder()
            .baseUrl("http://192.168.225.196:8081/identity/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APILogin.class);

    @GET("users/{userName}")
    Call<Account> getAccountByUserName(@Path("userName") String userName);
}
