package com.example.finalproject.employee.add_new_patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.finalproject.R;
import com.example.finalproject.api.api_patient.CallApiPatient;
import com.example.finalproject.api.api_patient.OnListenPatientAPI;
import com.example.finalproject.employee.add_new_patient.recycle_view.FieldAdapter;
import com.example.finalproject.employee.add_new_patient.recycle_view.FieldPatientInterface;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.example.finalproject.employee.main_activity_employee.MainScreenEmployee;
import com.example.finalproject.sharePreference.DataLocalManager;
import com.example.finalproject.utilities.Utilities;

import java.util.Calendar;

public class AddNewPatient extends AppCompatActivity implements AddNewPatientInterface{
    private AddNewPatientPresenter addNewPatientPresenter = new AddNewPatientPresenter(this,this);
    private FieldAdapter fieldAdapter ;
    private RecyclerView rcvInformationPatient;
    private CardView cardViewSave;
    private EditText focusedEditText;
    private CallApiPatient call = new CallApiPatient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        initUi();
        Utilities.SignOut(this);
        fieldAdapter = new FieldAdapter(this, new FieldPatientInterface() {
            @Override
            public void setDate(EditText editText) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddNewPatient.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }

                        },
                        year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvInformationPatient.setLayoutManager(linearLayoutManager);
        addNewPatientPresenter.getAllFieldPatient();

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(AddNewPatient.this);
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

        cardViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientInformation patientInformation = DataLocalManager.getPatientInformation();
                if(addNewPatientPresenter.hasNullFields(patientInformation)){
                    patientInformation.setInputStatus("unfinished");
                }
                else {
                    patientInformation.setInputStatus("finished");
                }
                call.createPatient(patientInformation, new OnListenPatientAPI() {
                    @Override
                    public void createPatientSuccess() {
                        Intent intent = new Intent(AddNewPatient.this, MainScreenEmployee.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void createPatientFall() {
                        System.out.println("Call API fall");
                    }
                });
                System.out.println(patientInformation.toString());

            }
        });
    }

    private void initUi() {
        rcvInformationPatient = findViewById(R.id.rcv_information_patient);
        cardViewSave= findViewById(R.id.card_view_save);
    }

    @Override
    public void setDataOnRecycleView() {
        fieldAdapter.setData(DataLocalManager.getAllFieldOfPatient());
        rcvInformationPatient.setAdapter(fieldAdapter);
    }

}