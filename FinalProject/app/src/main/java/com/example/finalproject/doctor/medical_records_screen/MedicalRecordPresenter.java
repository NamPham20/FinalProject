package com.example.finalproject.doctor.medical_records_screen;

import android.content.Context;

import com.example.finalproject.api.api_get_medical_record_field.CallApiMedical;
import com.example.finalproject.api.api_get_medical_record_field.OnListenMedicalApi;
import com.example.finalproject.doctor.medical_records_screen.recycle_view.MedicalRecordFields;
import com.example.finalproject.sharePreference.DataLocalManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordPresenter {

    private Context context;
    private MedicalRecordInterface medicalRecordInterface;
    private CallApiMedical call = new CallApiMedical();

    public MedicalRecordPresenter(Context context, MedicalRecordInterface medicalRecordInterface) {
        this.context = context;
        this.medicalRecordInterface = medicalRecordInterface;
    }

    public void getALLFieldOfMedicalRecord(){
        call.getAllFieldOfMedicalRecord(new OnListenMedicalApi() {
            @Override
            public void OnSuccess() {
                medicalRecordInterface.setDataOnRcv();
            }

            @Override
            public void OnFall() {

            }
        });
    }

    public List<MedicalRecordFields> getDataForDiagnosis(String str){
        List<MedicalRecordFields> medicalRecordFields = DataLocalManager.getAllFieldOfMedical();
        return medicalRecordFields.stream().filter(MedicalRecordFields ->MedicalRecordFields.getDiagnoseType().trim().equals(str))
                .collect(Collectors.toList());
    }

    public Boolean checkExpand(Boolean isExpand){
        if(isExpand){
            medicalRecordInterface.collapse();
            return false;
        }else {
            medicalRecordInterface.expand();
            return true;
        }

    }

}
