package com.example.finalproject.api.api_get_medical_record_field;

import com.example.finalproject.api.api_get_field_of_patient.ApiFieldOfPatient;
import com.example.finalproject.doctor.medical_records_screen.recycle_view.MedicalRecordFields;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIFieldOfMedicalRecord {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    APIFieldOfMedicalRecord api = new Retrofit.Builder()
            .baseUrl("http://192.168.225.196:8081/identity/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIFieldOfMedicalRecord.class);


    @GET("medical_records")
    Call<List<MedicalRecordFields>> getAllField();
}
