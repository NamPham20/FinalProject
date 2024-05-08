package com.example.finalproject.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.doctor.main_activity_doctor.MainScreenDoctor;
import com.example.finalproject.employee.main_activity_employee.MainScreenEmployee;
import com.example.finalproject.manager.main_activity_manage.MainScreenMAnager;
import com.example.finalproject.module.Account;
import com.example.finalproject.sharePreference.DataLocalManager;
import com.example.finalproject.utilities.Utilities;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity  implements LoginInterface{
    private TextInputEditText textInputEditTextUserName;
    private TextInputEditText textInputEditTextPassword;
    private Button btnSignIn;
    private TextView tvErrorMessage;
    private EditText focusedEditText;
    Account account;

    private LoginPresenter loginPresenter = new LoginPresenter(this,this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        account = DataLocalManager.getAccount();
        if(account!= null){
            textInputEditTextUserName.setText(account.getUserName());
            textInputEditTextPassword.setText(account.getPassword());
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Objects.requireNonNull(textInputEditTextUserName.getText()).toString().trim();
                String password = Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim();
                loginPresenter.loginMethod(userName,password);
            }
        });

//hàm unFocus vào editText
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(MainActivity.this);
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
        textInputEditTextUserName = findViewById(R.id.textInputEdtUserName);
        textInputEditTextPassword = findViewById(R.id.textInputEdtPassword);
        btnSignIn                 = findViewById(R.id.btnSignIn);
        tvErrorMessage            = findViewById(R.id.tv_error_message);
    }


    @Override
    public void doctorLoginSuccess() {
        Intent intent = new Intent(this, MainScreenDoctor.class);
        startActivity(intent);
    }

    @Override
    public void employeeLoginSuccess() {
        Intent intent = new Intent(this, MainScreenEmployee.class);
        startActivity(intent);
    }

    @Override
    public void managerLoginSuccess() {
        Intent intent = new Intent(this, MainScreenMAnager.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onPasswordFall() {
        tvErrorMessage.setText("Mật Khẩu Không Đúng");
        tvErrorMessage.setTextColor(Color.parseColor("#D12B3C"));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvErrorMessage.setText("");
            }
        },2000);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLoginFall() {
        tvErrorMessage.setText("Tên đăng nhập hoặc mật khẩu không đúng");
        tvErrorMessage.setTextColor(Color.parseColor("#D12B3C"));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvErrorMessage.setText("");
            }
        },2000);
    }
}