package com.example.finalproject.api.api_patient;

import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIPatient {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    APIPatient api = new Retrofit.Builder()
            .baseUrl("http://192.168.225.196:8081/identity/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIPatient.class);


    @GET("patients")
    Call<List<PatientInformation>> getListPatient();


    @POST("patient/")
    Call<PatientInformation> createPatient(@Body PatientInformation patientInformation);
}
