package com.example.finalproject.employee.main_activity_employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.finalproject.R;
import com.example.finalproject.doctor.main_activity_doctor.recycle_view.PatientAdapter;
import com.example.finalproject.employee.add_new_patient.AddNewPatient;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformationAdapter;
import com.example.finalproject.sharePreference.DataLocalManager;
import com.example.finalproject.utilities.Utilities;

public class MainScreenEmployee extends AppCompatActivity implements MainScreenEmployeeInterface {
    private Button btnAddNewProfile;
    private RecyclerView rcvPatientList;
    private EditText focusedEditText;

    private PatientInformationAdapter adapter;
    private MainScreenEmployeePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_employee);
        initUi();
        Utilities.SignOut(this);
        adapter = new PatientInformationAdapter(this);
        presenter = new MainScreenEmployeePresenter(this,this);
        btnAddNewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientInformation patientInformation = new PatientInformation();
                DataLocalManager.savePatientInformation(patientInformation);
                Intent intent = new Intent(MainScreenEmployee.this, AddNewPatient.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvPatientList.setLayoutManager(linearLayoutManager);
        presenter.getListPatient();

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(MainScreenEmployee.this);
                View focusedView = getCurrentFocus();
                if (focusedView instanceof EditText) {
                    focusedEditText = (EditText) focusedView;
                }
                if (focusedEditText != null) {
                    focusedEditText.clearFocus();
                }
                return false;
            }
        });
    }

    private void initUi() {
        btnAddNewProfile = findViewById(R.id.bt_add_new_profile);
        rcvPatientList = findViewById(R.id.rcvPatientList);
    }

    @Override
    public void setDataOnRcv() {
        adapter.setData(presenter.getListPatientForRcv());
        rcvPatientList.setAdapter(adapter);
    }
}