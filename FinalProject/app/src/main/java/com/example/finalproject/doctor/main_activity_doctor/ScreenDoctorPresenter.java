package com.example.finalproject.doctor.main_activity_doctor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.finalproject.api.api_get_patient.CallAPI;
import com.example.finalproject.api.api_get_patient.OnApiListener;

public class ScreenDoctorPresenter {
    ScreenDoctorInterface screenDoctorInterface;
    Context context;
    CallAPI callAPI = new CallAPI();
    public ScreenDoctorPresenter(ScreenDoctorInterface screenDoctorInterface, Context context) {
        this.screenDoctorInterface = screenDoctorInterface;
        this.context = context;
    }

    public boolean activeSlider(boolean status) {
        if (status) {
            screenDoctorInterface.closeSlider();
            return  false;
        } else {
            screenDoctorInterface.openSlider();
            return  true;
        }
    }

    public void getListPatient(int page){
        callAPI.getPatientList(page, new OnApiListener() {
            @Override
            public void onDataLoaded() {
                screenDoctorInterface.setDataOnRecycleView();
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context,"Có lỗi xảy ra xin thử lại sau",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
