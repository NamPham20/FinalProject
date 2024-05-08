package com.example.finalproject.employee.main_activity_employee.recycle_view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.doctor.main_activity_doctor.recycle_view.PatientAdapter;

import java.util.List;

public class PatientInformationAdapter extends RecyclerView.Adapter<PatientInformationAdapter.PatientInformationViewHolder> {

    private Context context;
    private List<PatientInformation> patientInformationList;

    public PatientInformationAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<PatientInformation> patientInformationList){
        this.patientInformationList = patientInformationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatientInformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);
        return new PatientInformationAdapter.PatientInformationViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull PatientInformationViewHolder holder, int position) {
        PatientInformation patientInformation = patientInformationList.get(position);
        if(patientInformation==null){
            return;
        }

        holder.tvPatientId.setText(patientInformation.getId());
        holder.tvNumber.setText(String.valueOf(position));
        holder.tvPatientName.setText(patientInformation.getFullName());
        holder.imvStatus.setImageDrawable(patientInformation.getInputStatus().equals("finished")? context.getDrawable(R.drawable.status_green)
                                                                    :context.getDrawable(R.drawable.status_red) );
        holder.layoutItemPatient.setBackgroundColor(position%2==0? Color.parseColor("#FFFFF0"):Color.parseColor("#FFC3D7") );
    }

    @Override
    public int getItemCount() {
        if(patientInformationList!=null){
            return patientInformationList.size();
        }
        return 0;
    }

    public static class PatientInformationViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumber;
        private TextView tvPatientId;
        private TextView tvPatientName;
        private ImageView imvStatus;
        private LinearLayout layoutItemPatient;

        public PatientInformationViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPatientId = itemView.findViewById(R.id.tvPatientId);
            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            imvStatus = itemView.findViewById(R.id.imvStatus);
            layoutItemPatient = itemView.findViewById(R.id.layoutItemPatient);
        }
    }
}
