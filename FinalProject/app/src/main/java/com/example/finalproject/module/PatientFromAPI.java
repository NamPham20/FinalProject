package com.example.finalproject.module;

public class PatientFromAPI {

        private float id;
        private String redcap_record_id;
        private String redcap_research_id;
        private String first_name;
        private String last_name;
        private String date_of_birth;
        private String age = null;
        private String created_date;
        private String gender;
        private String contact_number;
        private String email = null;
        private String social_id = null;
        private String healthcare_id = null;
        private String address = null;
        private String emergency_contact_name = null;
        private String emergency_contact_number = null;
        private float hospital;
        private String created_by_doctor = null;




    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getRedcap_record_id() {
        return redcap_record_id;
    }

    public void setRedcap_record_id(String redcap_record_id) {
        this.redcap_record_id = redcap_record_id;
    }

    public String getRedcap_research_id() {
        return redcap_research_id;
    }

    public void setRedcap_research_id(String redcap_research_id) {
        this.redcap_research_id = redcap_research_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocial_id() {
        return social_id;
    }

    public void setSocial_id(String social_id) {
        this.social_id = social_id;
    }

    public String getHealthcare_id() {
        return healthcare_id;
    }

    public void setHealthcare_id(String healthcare_id) {
        this.healthcare_id = healthcare_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public String getEmergency_contact_number() {
        return emergency_contact_number;
    }

    public void setEmergency_contact_number(String emergency_contact_number) {
        this.emergency_contact_number = emergency_contact_number;
    }

    public float getHospital() {
        return hospital;
    }

    public void setHospital(float hospital) {
        this.hospital = hospital;
    }

    public String getCreated_by_doctor() {
        return created_by_doctor;
    }

    public void setCreated_by_doctor(String created_by_doctor) {
        this.created_by_doctor = created_by_doctor;
    }
}
