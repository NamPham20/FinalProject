package com.example.finalproject.sharePreference;

import android.content.Context;

import com.example.finalproject.doctor.medical_records_screen.recycle_view.MedicalRecordFields;
import com.example.finalproject.employee.add_new_patient.recycle_view.FieldOfPatient;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.example.finalproject.module.Account;
import com.example.finalproject.module.PatientFromAPI;
import com.example.finalproject.module.PatientListFromApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class DataLocalManager {
    private static DataLocalManager instance;
    private SharePreferences sharePreferences;
    public static void init(Context mContext){
        instance = new DataLocalManager();
        instance.sharePreferences = new SharePreferences(mContext);
    }

    public static DataLocalManager getInstance(){
        if (instance==null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstLogin( Boolean isFirst){
        DataLocalManager.getInstance().sharePreferences.putBooleanValue("PREFERENCES_FIRST_LOGIN",isFirst);
    }

    public static Boolean getFirstLogin(){
        return DataLocalManager.getInstance().sharePreferences.getBooleanValue("PREFERENCES_FIRST_LOGIN");
    }

    public static void setUserName( String userName){
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCES_USER_NAME",userName);
    }

    public static String getUserName(){
        return DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCES_USER_NAME");
    }

    public static void setUserPassword( String password){
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCES_USER_PASSWORD",password);
    }

    public static String getUserPassword(){
        return DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCES_USER_PASSWORD");
    }

    public static void saveAccount(Account account){
        Gson gson = new Gson();
        String jsonAccount = gson.toJson(account);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCES_ACCOUNT",jsonAccount);
    }

    public static Account getAccount(){
        String jsonAccount  = DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCES_ACCOUNT");
        Gson gson = new Gson();
        return gson.fromJson(jsonAccount,new TypeToken<Account>(){}.getType());
    }


    public static void  savePatientFromApi(List<PatientFromAPI> listPatientFromApi){
        Gson gson = new Gson();
        String jsonListPatientFromApi = gson.toJson(listPatientFromApi);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCE_PATIENT", jsonListPatientFromApi);

    }

    public static List<PatientFromAPI> getPatientFromApi() {
       String jsonListPatientFromApi = DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCE_PATIENT");
       Gson gson = new Gson();
       return gson.fromJson(jsonListPatientFromApi,new TypeToken<List<PatientFromAPI>>(){}.getType());
    }

    public static void savePatientList( PatientListFromApi patientList){
        Gson gson = new Gson();
        String strPatientList= gson.toJson(patientList);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCES_PATIENT_LIST",strPatientList);
    }

    public static PatientListFromApi getPatientList(){
        String strPatientList= DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCES_PATIENT_LIST");
        Gson gson = new Gson();
        return gson.fromJson(strPatientList,PatientListFromApi.class);
    }

    public static void saveAllFiledOfPatient(List<FieldOfPatient> fieldOfPatients){
        Gson gson = new Gson();
        String jsonFileOfPatient = gson.toJson(fieldOfPatients);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCES_FIELD_OF_PATIENT",jsonFileOfPatient);
    }

    public static List<FieldOfPatient> getAllFieldOfPatient(){
        String jsonFileOfPatient = DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCES_FIELD_OF_PATIENT");
        Gson gson = new Gson();
        return gson.fromJson(jsonFileOfPatient,new TypeToken<List<FieldOfPatient>>(){}.getType());
    }

    public static void savePatientInformation(PatientInformation patientInformation){
        Gson gson = new Gson();
        String jsonPatientInformation = gson.toJson(patientInformation);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCE_PATIENT_INFORMATION",jsonPatientInformation);
    }

    public static PatientInformation getPatientInformation(){
        String jsonPatientInformation = DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCE_PATIENT_INFORMATION");
        Gson gson = new Gson();
        return gson.fromJson(jsonPatientInformation,new TypeToken<PatientInformation>(){}.getType());

    }

    public static void saveAllFiledOfMedical(List<MedicalRecordFields> medicalRecordFieldsList){
        Gson gson = new Gson();
        String jsonMedicalRecordFieldsList = gson.toJson(medicalRecordFieldsList);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCES_FIELD_OF_MEDICAL",jsonMedicalRecordFieldsList);
    }

    public static List<MedicalRecordFields> getAllFieldOfMedical(){
        String jsonMedicalRecordFieldsList = DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCES_FIELD_OF_MEDICAL");
        Gson gson = new Gson();
        return gson.fromJson(jsonMedicalRecordFieldsList,new TypeToken<List<MedicalRecordFields>>(){}.getType());
    }


    public static void  saveListPatientInformation(List<PatientInformation> patientInformationList){
        Gson gson = new Gson();
        String jsonPatientInformation = gson.toJson(patientInformationList);
        DataLocalManager.getInstance().sharePreferences.putStringValue("PREFERENCE_PATIENT_INFORMATION_LIST", jsonPatientInformation);

    }

    public static List<PatientInformation> getListPatientInformation() {
        String jsonPatientInformation = DataLocalManager.getInstance().sharePreferences.getStringValue("PREFERENCE_PATIENT_INFORMATION_LIST");
        Gson gson = new Gson();
        return gson.fromJson(jsonPatientInformation,new TypeToken<List<PatientInformation>>(){}.getType());
    }

}
