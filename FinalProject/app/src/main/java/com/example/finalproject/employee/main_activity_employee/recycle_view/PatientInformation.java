package com.example.finalproject.employee.main_activity_employee.recycle_view;

import androidx.annotation.NonNull;

public class PatientInformation {
    private String id;
    private String fullName;
    private String department;
    private String hospitalizedDay;
    private String hospitalizedYear;
    private String gender;
    private String age;
    private String address;
    private String phoneNumber;
    private String healthInsuranceCode;
    private String inputStatus;

    public String getInputStatus() {
        return inputStatus;
    }

    public void setInputStatus(String inputStatus) {
        this.inputStatus = inputStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHospitalizedDay() {
        return hospitalizedDay;
    }

    public void setHospitalizedDay(String hospitalizedDay) {
        this.hospitalizedDay = hospitalizedDay;
    }

    public String getHospitalizedYear() {
        return hospitalizedYear;
    }

    public void setHospitalizedYear(String hospitalizedYear) {
        this.hospitalizedYear = hospitalizedYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHealthInsuranceCode() {
        return healthInsuranceCode;
    }

    @Override
    public String toString() {
        return "PatientInformation{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", hospitalizedDay='" + hospitalizedDay + '\'' +
                ", hospitalizedYear='" + hospitalizedYear + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", healthInsuranceCode='" + healthInsuranceCode + '\'' +
                ", inputStatus='" + inputStatus + '\'' +
                '}';
    }

    public void setHealthInsuranceCode(String healthInsuranceCode) {
        this.healthInsuranceCode = healthInsuranceCode;


    }
}
