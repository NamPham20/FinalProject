package com.example.finalproject.doctor.main_activity_doctor.recycle_view;

import java.io.Serializable;

public class Patient implements Serializable {
    private int number;
    private String patientId;
    private String patientName;
    private int resourceId;


    public Patient(int number, String patientId, String patientName, int resourceId) {
        this.number = number;
        this.patientId = patientId;
        this.patientName = patientName;
        this.resourceId = resourceId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
