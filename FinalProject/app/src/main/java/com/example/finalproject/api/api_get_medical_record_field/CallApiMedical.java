package com.example.finalproject.api.api_get_medical_record_field;

import com.example.finalproject.doctor.medical_records_screen.recycle_view.MedicalRecordFields;
import com.example.finalproject.sharePreference.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiMedical {

    private List<MedicalRecordFields> medicalRecordFieldsList =new ArrayList<>();

    public void getAllFieldOfMedicalRecord( OnListenMedicalApi onListenMedicalApi){
        APIFieldOfMedicalRecord.api.getAllField().enqueue(new Callback<List<MedicalRecordFields>>() {
            @Override
            public void onResponse(Call<List<MedicalRecordFields>> call, Response<List<MedicalRecordFields>> response) {
                medicalRecordFieldsList = response.body();
                if(medicalRecordFieldsList!=null){
                    DataLocalManager.saveAllFiledOfMedical(medicalRecordFieldsList);
                    onListenMedicalApi.OnSuccess();
                }
                else {
                    onListenMedicalApi.OnFall();
                }
            }

            @Override
            public void onFailure(Call<List<MedicalRecordFields>> call, Throwable t) {
                onListenMedicalApi.OnFall();
            }
        });
    }
}
