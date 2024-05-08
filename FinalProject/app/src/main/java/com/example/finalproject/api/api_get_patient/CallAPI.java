package com.example.finalproject.api.api_get_patient;

import com.example.finalproject.module.PatientListFromApi;
import com.example.finalproject.sharePreference.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallAPI {

    PatientListFromApi patientListFromApi;

    public void getPatientList(int pageNumber, OnApiListener onApiListener){
        API.api.getPatientList(pageNumber).enqueue(new Callback<PatientListFromApi>() {
            @Override
            public void onResponse(Call<PatientListFromApi> call, Response<PatientListFromApi> response) {
                patientListFromApi = response.body();
                //List<PatientFromApi> patientFromApis = DataLocalManager.getPatientFromApi();
                if(patientListFromApi!= null){
                    DataLocalManager.savePatientList(patientListFromApi);
                    //patientFromApis.addAll(patientListFromApi.getResults());
                    DataLocalManager.savePatientFromApi(patientListFromApi.getResults());
                    onApiListener.onDataLoaded();
                }
            }

            @Override
            public void onFailure(Call<PatientListFromApi> call, Throwable t) {
                onApiListener.onFailure(t);
            }
        });
    }
}
