package com.example.finalproject.doctor.medical_records_screen.recycle_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageItemViewHolder> {

    Context context;
    List<ImageItem> imageItemList;

    public ImageItemAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ImageItem> imageItemList){
        this.imageItemList= imageItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new ImageItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemViewHolder holder, int position) {
        ImageItem imageItem =imageItemList.get(position);
        if (imageItem==null){
            return;
        }
        holder.ivtCt.setImageBitmap(imageItem.getBitmap());
    }

    @Override
    public int getItemCount() {
        if(imageItemList !=null){
            return imageItemList.size();
        }
        return 0;
    }


    public static class ImageItemViewHolder extends RecyclerView.ViewHolder{
        ImageView ivtCt;
        public ImageItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivtCt= itemView.findViewById(R.id.imvCt);
        }
    }
}
