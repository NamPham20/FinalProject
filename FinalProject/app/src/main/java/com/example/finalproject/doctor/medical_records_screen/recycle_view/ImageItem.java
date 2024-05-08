package com.example.finalproject.doctor.medical_records_screen.recycle_view;

import android.graphics.Bitmap;

public class ImageItem {
 private Bitmap bitmap ;

    public ImageItem(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
