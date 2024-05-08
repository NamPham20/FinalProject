package com.example.finalproject.employee.add_new_patient.recycle_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.employee.main_activity_employee.recycle_view.PatientInformation;
import com.example.finalproject.sharePreference.DataLocalManager;

import org.json.JSONArray;

import java.util.List;

public class FieldAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_FIELD_NORMAL = 1;
    private static final int TYPE_FIELD_DATE = 2;
    private static final int TYPE_FIELD_GENDER = 3;
    private PatientInformation patientInformation= new PatientInformation();


    private Context context;
    private List<FieldOfPatient> fieldOfPatients;
    private FieldPatientInterface fieldPatientInterface;

    public FieldAdapter(Context context, FieldPatientInterface fieldPatientInterface) {
        this.context = context;
        this.fieldPatientInterface = fieldPatientInterface;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<FieldOfPatient> fieldOfPatients){
        this.fieldOfPatients = fieldOfPatients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(TYPE_FIELD_NORMAL == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_item,parent,false);
            return new NormalViewHolder(view);
        }else if(TYPE_FIELD_DATE == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item,parent,false);
            return new DateViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gender_item,parent,false);
            return new GenderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FieldOfPatient fieldOfPatient = fieldOfPatients.get(position);
        if(fieldOfPatient == null){
            return;
        }

        if(TYPE_FIELD_NORMAL== holder.getItemViewType()){

            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            normalViewHolder.tvNameField.setText(fieldOfPatient.getNameField());
            if(fieldOfPatient.getNameField().trim().equals("Mã Bệnh Nhân")){
                normalViewHolder.edtValueField.setEnabled(false);
            }
            normalViewHolder.edtValueField.setInputType(fieldOfPatient.getValueType().equals("number")? InputType.TYPE_CLASS_NUMBER:InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            normalViewHolder.edtValueField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    matchData(fieldOfPatient.getNameField(),normalViewHolder.edtValueField.getText().toString().trim());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else if(TYPE_FIELD_DATE == holder.getItemViewType()){

            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            dateViewHolder.tvNameField.setText(fieldOfPatient.getNameField());
            dateViewHolder.edtValueField.setInputType(InputType.TYPE_CLASS_DATETIME);
            dateViewHolder.imBtnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fieldPatientInterface.setDate(dateViewHolder.edtValueField);
                }
            });
            dateViewHolder.edtValueField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    matchData(fieldOfPatient.getNameField(),dateViewHolder.edtValueField.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else if(TYPE_FIELD_GENDER== holder.getItemViewType()){

            GenderViewHolder genderViewHolder = (GenderViewHolder) holder;
            genderViewHolder.tvNameField.setText(fieldOfPatient.getNameField());
            genderViewHolder.radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton radioButton = group.findViewById(checkedId);
                    String selectedValue = radioButton.getText().toString();
                    matchData(fieldOfPatient.getNameField(), selectedValue);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(fieldOfPatients!=null){
            return fieldOfPatients.size();
        }
        return 0;
    }

    JSONArray
    JObject
            NewtonJson



    @Override
    public int getItemViewType(int position) {
        FieldOfPatient fieldOfPatient = fieldOfPatients.get(position);
        if(fieldOfPatient.getType().equals("normal")){
            return  TYPE_FIELD_NORMAL;
        }else if(fieldOfPatient.getType().equals("date")){
            return  TYPE_FIELD_DATE;
        }else{
            return TYPE_FIELD_GENDER;
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameField;
        private EditText edtValueField;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameField = itemView.findViewById(R.id.tv_name_field);
            edtValueField = itemView.findViewById(R.id.edt_value_field);
        }
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameField;
        private EditText edtValueField;
        private ImageButton imBtnDate;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameField = itemView.findViewById(R.id.tv_name_field);
            edtValueField = itemView.findViewById(R.id.edt_value_field);
            imBtnDate = itemView.findViewById(R.id.ima_btn_calendar);
        }
    }

    public static class GenderViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameField;
        private RadioGroup radioGroupGender;

        public GenderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameField =  itemView.findViewById(R.id.tv_name_field);
            radioGroupGender = itemView.findViewById(R.id.radio_group_gender);
        }
    }

    public void matchData(String nameField,String data){
        switch (nameField){
            case "Mã bệnh nhân"     : patientInformation.setId(data);
                                    break;
            case "Tên bệnh nhân"        : patientInformation.setFullName(data);
                                    break;
            case "Khoa/Phòng điều trị" : patientInformation.setDepartment(data);
                                     break;
            case "Ngày vào viện"    : patientInformation.setHospitalizedDay(data);
                                    break;
            case "Năm vào viện"     : patientInformation.setHospitalizedYear(data);
                                    break;
            case "Giới tính"        : patientInformation.setGender(data);
                                    break;
            case "Tuổi"             : patientInformation.setAge(data);
                                    break;
            case "Địa chỉ"          : patientInformation.setAddress(data);
                                    break;
            case "Số điện thoại"    : patientInformation.setPhoneNumber(data);
                                    break;
            case "Mã bảo hiểm y tế" : patientInformation.setHealthInsuranceCode(data);
                                    break;

        }
        DataLocalManager.savePatientInformation(patientInformation);
    }

}
