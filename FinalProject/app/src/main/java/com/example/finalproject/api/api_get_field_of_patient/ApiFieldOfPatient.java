package com.example.finalproject.api.api_get_field_of_patient;

import com.example.finalproject.api.api_get_patient.API;
import com.example.finalproject.employee.add_new_patient.recycle_view.FieldOfPatient;
import com.example.finalproject.module.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiFieldOfPatient {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiFieldOfPatient api = new Retrofit.Builder()
            .baseUrl("http://192.168.225.196:8081/identity/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiFieldOfPatient.class);

    @GET("information_patient")
    Call<List<FieldOfPatient>> getAllField();
}
