package com.example.finalproject.employee.add_new_patient;

import android.content.Context;

import com.example.finalproject.api.api_get_field_of_patient.CallApiFieldOfPatient;
import com.example.finalproject.api.api_get_field_of_patient.OnListenGetField;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;

import java.lang.reflect.Field;

public class AddNewPatientPresenter {

    private Context context;
    private AddNewPatientInterface newPatientInterface;
    private CallApiFieldOfPatient call = new CallApiFieldOfPatient();

    public AddNewPatientPresenter(Context context, AddNewPatientInterface newPatientInterface) {
        this.context = context;
        this.newPatientInterface = newPatientInterface;
    }

    public void getAllFieldPatient() {
        call.getListFieldByApi(new OnListenGetField() {
            @Override
            public void onSuccess() {
                newPatientInterface.setDataOnRecycleView();
            }

            @Override
            public void onFall() {

            }

            @Override
            public void callOnFailure(Throwable t) {
                System.out.println("onFailure is called");
                t.printStackTrace();
            }
        });
    }

    public  boolean hasNullFields(PatientInformation patientInformation) {
        if (patientInformation == null) {
            return true;
        }
        for (Field field : patientInformation.getClass().getDeclaredFields()) {
            if (!field.getName().equals("inputStatus" )) {
                field.setAccessible(true);
                try {
                    Object value = field.get(patientInformation);
                    if (value == null) {
                        return true;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
            return false;

    }
}
