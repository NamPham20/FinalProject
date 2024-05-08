package com.example.finalproject.doctor.medical_records_screen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.doctor.medical_records_screen.recycle_view.ImageItem;
import com.example.finalproject.doctor.medical_records_screen.recycle_view.ImageItemAdapter;
import com.example.finalproject.doctor.medical_records_screen.recycle_view.MedicalFieldHandle;
import com.example.finalproject.doctor.medical_records_screen.recycle_view.MedicalRecordAdapter;
import com.example.finalproject.utilities.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MedicalRecordsScreen extends AppCompatActivity implements MedicalRecordInterface{

    private RecyclerView rcvFieldOfMedicalRecord;
    private CardView cardViewClinicalDiagnosis;
    private CardView cardViewCtImage;
    private CardView cardViewParaclinical;
    private TextView tvDiagnosisName;
    private TextView tvClinical;
    private TextView tvCtImage;
    private TextView tvParaclinical;
    private EditText focusedEditText;
    private LinearLayout llContent;
    private ImageButton imBtnExpand;

    private MedicalRecordAdapter medicalRecordAdapter;
    private MedicalRecordPresenter medicalRecordPresenter = new MedicalRecordPresenter(this,this);
    private int clinicalFlag =1;
    private int ctImageFlag=0;
    private int paraclinical=0;
    private Bitmap bitmap;
    List<ImageItem> imageItemList =  new ArrayList<>();
    ImageItemAdapter imageItemAdapter = new ImageItemAdapter(this);
    private Boolean isExpand= false;

    private final ActivityResultLauncher<Intent> mActi = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    System.out.println("onActivityResult is allow");
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imageItemList.add(new ImageItem(bitmap));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records_screen);
        initUi();
        Utilities.SignOut(this);
        medicalRecordAdapter = new MedicalRecordAdapter(this, new MedicalFieldHandle() {
            @Override
            public void setDate(EditText editText) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MedicalRecordsScreen.this,
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

            @Override
            public void chooseImage(RecyclerView recyclerView) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    chooseImageMethod();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MedicalRecordsScreen.this,RecyclerView.HORIZONTAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageItemAdapter.setData(imageItemList);
                            recyclerView.setAdapter(imageItemAdapter);
                        }
                    },500);
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvFieldOfMedicalRecord.setLayoutManager(linearLayoutManager);
        medicalRecordPresenter.getALLFieldOfMedicalRecord();
        cardViewClinicalDiagnosis.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(clinicalFlag ==1){
                    return;
                }else {
                    clinicalFlag =1;
                    ctImageFlag=0;
                    paraclinical=0;
                    cardViewClinicalDiagnosis.setCardBackgroundColor(Color.parseColor("#D12B3C"));
                    cardViewCtImage.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    cardViewParaclinical.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    tvClinical.setTextColor(Color.parseColor("#FFFFFFFF"));
                    tvCtImage.setTextColor(Color.parseColor("#FF000000"));
                    tvParaclinical.setTextColor(Color.parseColor("#FF000000"));
                    tvDiagnosisName.setText("Chẩn đoán lâm sàng");
                    medicalRecordAdapter.setData(medicalRecordPresenter.getDataForDiagnosis("clinical"));
                    rcvFieldOfMedicalRecord.setAdapter(medicalRecordAdapter);
                }
            }
        });

        cardViewCtImage.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(ctImageFlag==1){
                    return;
                }else {
                    clinicalFlag =0;
                    ctImageFlag=1;
                    paraclinical=0;
                    cardViewClinicalDiagnosis.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    cardViewCtImage.setCardBackgroundColor(Color.parseColor("#D12B3C"));
                    cardViewParaclinical.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    tvClinical.setTextColor(Color.parseColor("#FF000000"));
                    tvCtImage.setTextColor(Color.parseColor("#FFFFFFFF"));
                    tvParaclinical.setTextColor(Color.parseColor("#FF000000"));
                    tvDiagnosisName.setText("Chẩn đoán hình ảnh CT");
                    medicalRecordAdapter.setData(medicalRecordPresenter.getDataForDiagnosis("ct_image"));
                    rcvFieldOfMedicalRecord.setAdapter(medicalRecordAdapter);
                }
            }
        });

        cardViewParaclinical.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(paraclinical==1){
                    return;
                }else {
                    clinicalFlag =0;
                    ctImageFlag=0;
                    paraclinical=1;
                    cardViewClinicalDiagnosis.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    cardViewCtImage.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    cardViewParaclinical.setCardBackgroundColor(Color.parseColor("#D12B3C"));
                    tvClinical.setTextColor(Color.parseColor("#FF000000"));
                    tvCtImage.setTextColor(Color.parseColor("#FF000000"));
                    tvParaclinical.setTextColor(Color.parseColor("#FFFFFFFF"));
                    tvDiagnosisName.setText("Xét nghiệm cận Lâm sàng");
                    medicalRecordAdapter.setData(medicalRecordPresenter.getDataForDiagnosis("paraclinical"));
                    rcvFieldOfMedicalRecord.setAdapter(medicalRecordAdapter);
                }
            }
        });

        tvDiagnosisName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpand = medicalRecordPresenter.checkExpand(isExpand);
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(MedicalRecordsScreen.this);
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

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void chooseImageMethod() {
        if(checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("READ_EXTERNAL_STORAGE is allow");
            openGallery();
        }
        else {
            System.out.println("requestPermissions is called");
            String [] permission = {Manifest.permission.READ_MEDIA_IMAGES};
            ActivityCompat.requestPermissions(this,permission,100);
        }
    }
    private void openGallery() {
        System.out.println("openGallery is called");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        mActi.launch(Intent.createChooser(intent,"selected picture"));
    }

    private void initUi(){
          rcvFieldOfMedicalRecord = findViewById(R.id.rcv_medical_record_fields);
          cardViewClinicalDiagnosis = findViewById(R.id.card_view_chan_doan_lam_sang);
          cardViewCtImage= findViewById(R.id.card_view_chan_doan_hinh_anh_ct);
          cardViewParaclinical= findViewById(R.id.card_view_xet_nghiem_can_lam_sang);
          tvDiagnosisName = findViewById(R.id.tvDiagnosisName);
          tvClinical = findViewById(R.id.tv_title1);
          tvCtImage = findViewById(R.id.tv_title2);
          tvParaclinical = findViewById(R.id.tv_title3);
          llContent = findViewById(R.id.ll_content);
    }

    @Override
    public void setDataOnRcv() {
        medicalRecordAdapter.setData(medicalRecordPresenter.getDataForDiagnosis("clinical"));
        rcvFieldOfMedicalRecord.setAdapter(medicalRecordAdapter);
    }

    @Override
    public void expand() {
        ConstraintLayout.MarginLayoutParams layoutParams = (ConstraintLayout.MarginLayoutParams) llContent.getLayoutParams();
        layoutParams.setMargins(10, 10, 10, 80);

        llContent.setLayoutParams(layoutParams);
        llContent.requestLayout();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(R.drawable.collapse_while_icon,null);
        tvDiagnosisName.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
    }

    @Override
    public void collapse() {
        ConstraintLayout.MarginLayoutParams layoutParams = (ConstraintLayout.MarginLayoutParams) llContent.getLayoutParams();

        int marginStart = getResources().getDimensionPixelSize(R.dimen.margin_start); // or 10dp
        int marginTop = getResources().getDimensionPixelSize(R.dimen.margin_top); // or 130dp
        int marginEnd = getResources().getDimensionPixelSize(R.dimen.margin_end); // or 10dp
        int marginBottom = getResources().getDimensionPixelSize(R.dimen.margin_bottom);

        layoutParams.setMargins(marginStart, marginTop, marginEnd, marginBottom);

        llContent.setLayoutParams(layoutParams);
        llContent.requestLayout();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(R.drawable.expand_icon,null);
        tvDiagnosisName.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
    }
}