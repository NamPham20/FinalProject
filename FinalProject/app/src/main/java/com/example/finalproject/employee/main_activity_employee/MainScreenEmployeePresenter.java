package com.example.finalproject.employee.main_activity_employee;

import android.content.Context;

import com.example.finalproject.api.api_patient.CallApiPatient;
import com.example.finalproject.api.api_patient.OnListenGetListPatientFromAPI;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.example.finalproject.sharePreference.DataLocalManager;

import java.util.List;

public class MainScreenEmployeePresenter {
    private MainScreenEmployeeInterface mainScreenEmployeeInterface;
    private Context context;
    private CallApiPatient call= new CallApiPatient();

    public MainScreenEmployeePresenter(MainScreenEmployeeInterface mainScreenEmployeeInterface, Context context) {
        this.mainScreenEmployeeInterface = mainScreenEmployeeInterface;
        this.context = context;
    }

    public void getListPatient(){
        call.getListPatient(new OnListenGetListPatientFromAPI() {
            @Override
            public void getListPatientSuccess() {
                mainScreenEmployeeInterface.setDataOnRcv();
            }

            @Override
            public void getListPatientFall() {

            }
        });
    }

    public List<PatientInformation> getListPatientForRcv(){
        return DataLocalManager.getListPatientInformation();
    }
}
