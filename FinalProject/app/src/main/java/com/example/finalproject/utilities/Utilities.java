package com.example.finalproject.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.finalproject.R;
import com.example.finalproject.login.MainActivity;
import com.example.finalproject.sharePreference.DataLocalManager;


public class Utilities {
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void onResetClicked(View view) {
        System.out.println("onResetClicked  is clicked");
        int radioGroupId = findParentRadioGroupId(view);
        ViewParent parentRadioGroup = (view.getParent()).getParent();
        System.out.println("radioGroupId  :" +radioGroupId);
        if (radioGroupId != 0) {
            RadioGroup radioGroup = ((View)parentRadioGroup).findViewById(radioGroupId);
            System.out.println("radioGroup  :" +radioGroup);
            if (radioGroup != null) {
                radioGroup.clearCheck();
                // Thực hiện các hành động khác cần thiết để đặt lại RadioGroup
            }
        }
    }
    private static int findParentRadioGroupId(View view) {
        ViewParent parent = view.getParent();
        while (parent != null) {
            if (parent instanceof RadioGroup) {
                return ((RadioGroup) parent).getId();
            }
            parent = parent.getParent();
        }
        return 0;
    }
    public static void SignOut(Activity activity){
        ImageView  imvSignOut = activity.findViewById(R.id.imvSignOut);
        imvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLocalManager.setFirstLogin(false);
                Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
                activity.startActivity(intent);
                activity.finishAffinity();
            }
        });

    }

}
