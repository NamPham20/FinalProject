package com.example.finalproject.doctor.medical_records_screen.recycle_view;

import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

public interface MedicalFieldHandle {
    public void setDate(EditText editText);
    public void chooseImage(RecyclerView recyclerView);
}
