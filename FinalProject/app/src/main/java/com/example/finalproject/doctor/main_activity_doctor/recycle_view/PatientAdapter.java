package com.example.finalproject.doctor.main_activity_doctor.recycle_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private Context mContext;
    private List<Patient> mListPatient;
    private IClickItem iClickItem;

    public PatientAdapter(Context mContext, IClickItem iClickItem) {
        this.mContext = mContext;
        this.iClickItem =iClickItem;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Patient> mListPatient){
        this.mListPatient =mListPatient;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);
        return new PatientViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(mListPatient !=null){
            return mListPatient.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = mListPatient.get(position);
        if(patient == null){
            return  ;
        }
        holder.tvNumber.setText(String.valueOf(patient.getNumber()));
        holder.tvPatientId.setText(patient.getPatientId());
        holder.tvPatientName.setText(patient.getPatientName());
        holder.imvStatus.setImageResource(patient.getResourceId());
        holder.layoutItemPatient.setBackgroundColor(patient.getNumber()%2==0? Color.parseColor("#FFFFF0"):Color.parseColor("#FFC3D7") );
        holder.layoutItemPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.onClickItemPatient(patient);
            }
        });

    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumber;
        private TextView tvPatientId;
        private TextView tvPatientName;
        private ImageView imvStatus;
        private LinearLayout layoutItemPatient;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);

        }

        void init(View itemView){
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPatientId = itemView.findViewById(R.id.tvPatientId);
            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            imvStatus = itemView.findViewById(R.id.imvStatus);
            layoutItemPatient = itemView.findViewById(R.id.layoutItemPatient);

        }

    }


}
