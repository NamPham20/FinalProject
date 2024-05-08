package com.example.finalproject.doctor.medical_records_screen.recycle_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.employee.add_new_patient.recycle_view.FieldAdapter;

import java.util.List;
import java.util.logging.LogRecord;

public class MedicalRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FIELD_NORMAL = 1;
    private static final int TYPE_FIELD_DATE = 2;
    private static final int TYPE_FIELD_TWO_CHOICE = 3;
    private static final int TYPE_FIELD_THREE_CHOICE = 4;
    private static final int TYPE_FIELD_TITLE =5;
    private static final int TYPE_FIELD_UNIT = 6;
    private static final int TYPE_FIELD_CHOOSE_IMAGE= 7;
    private static final int TYPE_FIELD_MULTIPLE_SELECT=8;

    private List<MedicalRecordFields> medicalRecordFieldsList;
    private Context context;
    private MedicalFieldHandle handle;
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<MedicalRecordFields> medicalRecordFieldsList){
        this.medicalRecordFieldsList= medicalRecordFieldsList;
        notifyDataSetChanged();
    }

    public MedicalRecordAdapter(Context context,MedicalFieldHandle handle) {
        this.context = context;
        this.handle = handle;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_FIELD_NORMAL:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_item,parent,false);
                return new MedicalRecordAdapter.NormalViewHolder(view);
            }
            case TYPE_FIELD_DATE:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item,parent,false);
                return new MedicalRecordAdapter.DateViewHolder(view);
            }
            case TYPE_FIELD_TWO_CHOICE:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_choice_item,parent,false);
                return new MedicalRecordAdapter.TwoChoiceViewHolder(view);
            }
            case TYPE_FIELD_THREE_CHOICE:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_choice_item,parent,false);
                return new MedicalRecordAdapter.ThreeChoiceViewHolder(view);
            }
            case TYPE_FIELD_TITLE:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item,parent,false);
                return new MedicalRecordAdapter.TitleViewHolder(view);
            }
            case TYPE_FIELD_UNIT:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.units_item,parent,false);
                return new MedicalRecordAdapter.UnitsViewHolder(view);
            }
            case TYPE_FIELD_CHOOSE_IMAGE:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_image_item,parent,false);
                return new MedicalRecordAdapter.ChooseImageViewHolder(view);
            }
            case TYPE_FIELD_MULTIPLE_SELECT:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_select_item,parent,false);
                return new MedicalRecordAdapter.MultipleSelectViewHolder(view);
            }
            default: throw new IllegalArgumentException("Invalid viewType: " + viewType);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MedicalRecordFields medicalRecordFields = medicalRecordFieldsList.get(position);
        if(medicalRecordFields== null){
            return;
        }

        switch (holder.getItemViewType()){
            case TYPE_FIELD_NORMAL:{
                NormalViewHolder normalViewHolder= (NormalViewHolder) holder;
                normalViewHolder.tvNameField.setText(medicalRecordFields.getNameField());
                normalViewHolder.edtValueField.setInputType(medicalRecordFields.getValueType().equals("number")? InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER
                        :InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                break;
            }
            case TYPE_FIELD_DATE:{
                DateViewHolder dateViewHolder =(DateViewHolder) holder;
                dateViewHolder.tvNameField.setText(medicalRecordFields.getNameField());
                dateViewHolder.edtValueField.setInputType(InputType.TYPE_CLASS_DATETIME);
                dateViewHolder.imBtnDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handle.setDate(dateViewHolder.edtValueField);
                    }
                });
                break;
            }
            case TYPE_FIELD_TWO_CHOICE:{
                TwoChoiceViewHolder twoChoiceViewHolder =(TwoChoiceViewHolder) holder;
                twoChoiceViewHolder.tvNameField.setText(medicalRecordFields.getNameField());
                String [] title = medicalRecordFields.getValueList().split(",");
                twoChoiceViewHolder.radioButtonTitle1.setText(title[0]);
                twoChoiceViewHolder.radioButtonTitle2.setText(title[1]);
                twoChoiceViewHolder.tvReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        twoChoiceViewHolder.radioGroup.clearCheck();
                    }
                });
                break;
            }
            case TYPE_FIELD_THREE_CHOICE:{
                ThreeChoiceViewHolder threeChoiceViewHolder = (ThreeChoiceViewHolder) holder;
                threeChoiceViewHolder.tvNameField.setText(medicalRecordFields.getNameField());
                threeChoiceViewHolder.tvReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        threeChoiceViewHolder.radioGroup.clearCheck();
                    }
                });
                break;
            }
            case TYPE_FIELD_TITLE:{
                TitleViewHolder titleViewHolder= (TitleViewHolder) holder;
                titleViewHolder.tvNameTitle.setText(medicalRecordFields.getNameField());
                break;
            }
            case TYPE_FIELD_CHOOSE_IMAGE:{
                ChooseImageViewHolder chooseImageViewHolder = (ChooseImageViewHolder) holder;
                chooseImageViewHolder.imBtnChooseImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handle.chooseImage(chooseImageViewHolder.rcvShowImagePicked);
                    }
                });
                break;
            }
            case  TYPE_FIELD_MULTIPLE_SELECT:{
                MultipleSelectViewHolder multipleSelectViewHolder = (MultipleSelectViewHolder) holder;
                multipleSelectViewHolder.tvNameFiled.setText(medicalRecordFields.getNameField());
                String [] str = medicalRecordFields.getValueList().split(",");
                for (String item : str){
                    CheckBox checkBox = new CheckBox(context);
                    checkBox.setText(item);
                    checkBox.setTextSize(20);
                    multipleSelectViewHolder.linearLayoutListCheckbox.addView(checkBox);
                }
                break;
            }
            case TYPE_FIELD_UNIT:{
                UnitsViewHolder unitsViewHolder= (UnitsViewHolder) holder;
                unitsViewHolder.tvNameField.setText(medicalRecordFields.getNameField());
                unitsViewHolder.edtValue.setInputType( InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
                unitsViewHolder.imvSupport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unitsViewHolder.tvNotice.setVisibility(View.VISIBLE);
                        unitsViewHolder.tvNotice.setText(medicalRecordFields.getValueList());
                        unitsViewHolder.imvSupport.setVisibility(View.GONE);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                unitsViewHolder.tvNotice.setVisibility(View.GONE);
                                unitsViewHolder.imvSupport.setVisibility(View.VISIBLE);
                            }
                        },1000);
                    }
                });


            }
        }

    }

    @Override
    public int getItemCount() {
        if(medicalRecordFieldsList!= null){
            return medicalRecordFieldsList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        MedicalRecordFields medicalRecordFields= medicalRecordFieldsList.get(position);
        switch (medicalRecordFields.getType()){
            case "normal"           :return TYPE_FIELD_NORMAL;
            case "date"             :return TYPE_FIELD_DATE;
            case "title"            :return TYPE_FIELD_TITLE;
            case "two_choice"       :return TYPE_FIELD_TWO_CHOICE;
            case "three_choice"     :return TYPE_FIELD_THREE_CHOICE;
            case "choose_image"     :return TYPE_FIELD_CHOOSE_IMAGE;
            case "units"            :return TYPE_FIELD_UNIT;
            case "multiple_select"  :return TYPE_FIELD_MULTIPLE_SELECT;
            default: return 1;
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

    public static class TwoChoiceViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameField;
        private RadioGroup radioGroup;
        private RadioButton radioButtonTitle1;
        private RadioButton radioButtonTitle2;
        private TextView tvReset;
        public TwoChoiceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameField = itemView.findViewById(R.id.tv_name_field);
            radioGroup =  itemView.findViewById(R.id.radio_group_two_choice);
            radioButtonTitle1 = itemView.findViewById(R.id.radio_btn_title1);
            radioButtonTitle2 =itemView.findViewById(R.id.radio_btn_title2);
            tvReset = itemView.findViewById(R.id.tv_reset);

        }
    }

    public static class ThreeChoiceViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameField;
        private RadioGroup radioGroup;
        private TextView tvReset;
        public ThreeChoiceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameField = itemView.findViewById(R.id.tv_name_field);
            radioGroup =  itemView.findViewById(R.id.radio_group_three_choice);
            tvReset = itemView.findViewById(R.id.tv_reset);

        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTitle = itemView.findViewById(R.id.tv_name_title);
        }
    }

    public static class UnitsViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameField;
        private EditText edtValue;
        private TextView tvNotice;
        private ImageView imvSupport;

        public UnitsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameField = itemView.findViewById(R.id.tv_name_field);
            edtValue = itemView.findViewById(R.id.edt_value);
            tvNotice = itemView.findViewById(R.id.tv_notice);
            imvSupport = itemView.findViewById(R.id.imv_support);

        }
    }

    public static class ChooseImageViewHolder extends RecyclerView.ViewHolder{

        private ImageButton imBtnChooseImage;
        private RecyclerView rcvShowImagePicked;

        public ChooseImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imBtnChooseImage = itemView.findViewById(R.id.imBtnChooseImage);
            rcvShowImagePicked = itemView.findViewById(R.id.rcvImages);
        }
    }

    public static class MultipleSelectViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameFiled;
        private LinearLayout linearLayoutListCheckbox;

        public MultipleSelectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameFiled = itemView.findViewById(R.id.tv_name_field);
            linearLayoutListCheckbox = itemView.findViewById(R.id.linearLayout_list_checkbox);
        }
    }
}
