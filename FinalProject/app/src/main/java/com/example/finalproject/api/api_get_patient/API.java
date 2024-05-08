package com.example.finalproject.api.api_get_patient;

import com.example.finalproject.module.PatientListFromApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    API api = new Retrofit.Builder()
            .baseUrl("http://api.quanlybenhan.com:50220/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API.class);

    @GET("patient/")
    Call<PatientListFromApi> getPatientList(@Query("page") int page);

}
