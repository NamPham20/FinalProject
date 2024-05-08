package com.example.finalproject.api.api_get_field_of_patient;

import com.example.finalproject.employee.add_new_patient.recycle_view.FieldOfPatient;
import com.example.finalproject.module.Account;
import com.example.finalproject.sharePreference.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiFieldOfPatient {
    List<FieldOfPatient> fieldOfPatients = new ArrayList<>();

    public void getListFieldByApi( OnListenGetField onListenGetField){
        ApiFieldOfPatient.api.getAllField().enqueue(new Callback<List<FieldOfPatient>>() {
            @Override
            public void onResponse(Call<List<FieldOfPatient>> call, Response<List<FieldOfPatient>> response) {
                fieldOfPatients = response.body();
                if (fieldOfPatients != null){
                    DataLocalManager.saveAllFiledOfPatient(fieldOfPatients);
                    onListenGetField.onSuccess();
                }else {
                    onListenGetField.onFall();
                }
            }

            @Override
            public void onFailure(Call<List<FieldOfPatient>> call, Throwable t) {
                onListenGetField.callOnFailure(t);
            }
        });
    }
}
