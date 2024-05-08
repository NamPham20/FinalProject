package com.example.finalproject.doctor.main_activity_doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.doctor.main_activity_doctor.recycle_view.IClickItem;
import com.example.finalproject.doctor.main_activity_doctor.recycle_view.Patient;
import com.example.finalproject.doctor.main_activity_doctor.recycle_view.PatientAdapter;
import com.example.finalproject.doctor.medical_records_screen.MedicalRecordsScreen;
import com.example.finalproject.login.MainActivity;
import com.example.finalproject.module.PatientFromAPI;
import com.example.finalproject.module.PatientListFromApi;
import com.example.finalproject.sharePreference.DataLocalManager;
import com.example.finalproject.utilities.Utilities;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class MainScreenDoctor extends AppCompatActivity implements ScreenDoctorInterface {
    private ImageView imvBtnSlider;
    private LinearLayout lLSlider;
    private EditText focusedEditText;
    private RecyclerView rcvPatientList;
    private TextView tvBefore;
    private EditText edtPageNumber;
    private TextView tvTotalPage;
    private TextView tvAfter;
    private TextInputEditText textInputEditTextSearch;

    private boolean checkSlider = false;
    private ScreenDoctorPresenter doctorPresenter = new ScreenDoctorPresenter(this,this);
    private int pageNumber =1 ;
    private int pageNumberMax;
    List<PatientFromAPI> patientFromApis;
    private PatientAdapter patientAdapter = new PatientAdapter(this, new IClickItem() {
        @Override
        public void onClickItemPatient(Patient patient) {
            Intent intent = new Intent(MainScreenDoctor.this, MedicalRecordsScreen.class);
            intent.putExtra("patientInformation", patient);
            startActivity(intent);
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_doctor);

        initUi();
        Utilities.SignOut(this);
        if(DataLocalManager.getPatientList()!=null){
            pageNumberMax =(int) DataLocalManager.getPatientList().getCount();
        }else {
            pageNumberMax=100;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvPatientList.setLayoutManager(linearLayoutManager);
        doctorPresenter.getListPatient(pageNumber);
        imvBtnSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSlider= doctorPresenter.activeSlider(checkSlider);
            }
        });

        tvAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber<pageNumberMax){
                    pageNumber++;
                    doctorPresenter.getListPatient(pageNumber);
                    edtPageNumber.setHint(String.valueOf(pageNumber));
                }
            }
        });

        tvBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageNumber>1){
                    pageNumber--;
                    doctorPresenter.getListPatient(pageNumber);
                    edtPageNumber.setHint(String.valueOf(pageNumber));
                }

            }
        });


        edtPageNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    pageNumber = Integer.parseInt(edtPageNumber.getText().toString()) ;
                    doctorPresenter.getListPatient(pageNumber);

                }

                return true;
            }
        });


        //hàm unFocus vào editText
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(MainScreenDoctor.this);
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
        imvBtnSlider = findViewById(R.id.imv_btn_slider);
        lLSlider = findViewById(R.id.lLSlider);
        rcvPatientList = findViewById(R.id.rcvPatientList);
        textInputEditTextSearch = findViewById(R.id.tIEditSearch);
        tvBefore= findViewById(R.id.tvBefore);
        edtPageNumber= findViewById(R.id.edtPageNumber);
        tvAfter= findViewById(R.id.tvAfter);
        tvTotalPage=findViewById(R.id.tvTotalPage);
    }

    @Override
    public void openSlider() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_animation);
        imvBtnSlider.setImageResource(R.drawable.icon_sider_1);
        lLSlider.setVisibility(View.VISIBLE);
        lLSlider.startAnimation(animation);
    }

    @Override
    public void closeSlider() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_animation);
        imvBtnSlider.setImageResource(R.drawable.icon_sider_2);
        lLSlider.setVisibility(View.GONE);
        lLSlider.startAnimation(animation);
    }

    @Override
    public void setDataOnRecycleView() {
        patientAdapter.setData(getListPatientFromAPI());
        rcvPatientList.setAdapter(patientAdapter);
    }

    private List<Patient> getListPatientFromAPI () {
            List<Patient> mListPatient = new ArrayList<>();
        patientFromApis = DataLocalManager.getPatientFromApi();
        if(patientFromApis==null){
            return null;
        }
        for (int i=0;i<patientFromApis.size();i++){
            mListPatient.add(new Patient((int) patientFromApis.get(i).getId(),
                    patientFromApis.get(i).getRedcap_research_id(),
                    patientFromApis.get(i).getFirst_name(),
                    R.drawable.status_red));
        }
        return mListPatient;
    }
}