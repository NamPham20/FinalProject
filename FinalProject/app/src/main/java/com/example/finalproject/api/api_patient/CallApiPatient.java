package com.example.finalproject.api.api_patient;

import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.example.finalproject.sharePreference.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiPatient {

    List<PatientInformation> patientInformationList =new ArrayList<>();

    public void createPatient(PatientInformation patientInformation , OnListenPatientAPI onListenPatientAPI){
        APIPatient.api.createPatient(patientInformation).enqueue(new Callback<PatientInformation>() {
            @Override
            public void onResponse(Call<PatientInformation> call, Response<PatientInformation> response) {
                PatientInformation patientInformation = response.body();
                if (patientInformation!=null){
                    onListenPatientAPI.createPatientSuccess();
                }
                else {
                    onListenPatientAPI.createPatientFall();
                }
            }

            @Override
            public void onFailure(Call<PatientInformation> call, Throwable t) {
                onListenPatientAPI.createPatientFall();
            }
        });
    }

    public void getListPatient( OnListenGetListPatientFromAPI onListen){
        APIPatient.api.getListPatient().enqueue(new Callback<List<PatientInformation>>() {
            @Override
            public void onResponse(Call<List<PatientInformation>> call, Response<List<PatientInformation>> response) {
                patientInformationList = response.body();

                if(patientInformationList!=null){
                    DataLocalManager.saveListPatientInformation(patientInformationList);
                    onListen.getListPatientSuccess();
                }
                else {
                    onListen.getListPatientFall();
                }
            }

            @Override
            public void onFailure(Call<List<PatientInformation>> call, Throwable t) {
                onListen.getListPatientFall();
            }
        });
    }
}
