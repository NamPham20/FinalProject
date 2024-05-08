package com.example.finalproject.login;

import android.content.Context;

import com.example.finalproject.api.api_login.CallApiLogin;
import com.example.finalproject.api.api_login.OnListenSuccessInterface;
import com.example.finalproject.module.Account;
import com.example.finalproject.sharePreference.DataLocalManager;

public class LoginPresenter {

    Context context;
    LoginInterface loginInterface;
    CallApiLogin callApiLogin = new CallApiLogin();

    public LoginPresenter(Context context, LoginInterface loginInterface) {
        this.context = context;
        this.loginInterface = loginInterface;
    }

    public void loginMethod(String userName,String password){
        callApiLogin.getUserByUserName(userName, new OnListenSuccessInterface() {
            @Override
            public void dataOk() {
                Account account = DataLocalManager.getAccount();
                if(password.equals(account.getPassword())){
                    switch (account.getPosition()){
                        case "Doctor"   : loginInterface.doctorLoginSuccess();
                                            break;
                        case "Employee" :loginInterface.employeeLoginSuccess();
                                            break;
                        case "Manage"   : loginInterface.managerLoginSuccess();
                                            break;
                        default         : loginInterface.onLoginFall();
                                            break;
                    }
                }else {
                    loginInterface.onPasswordFall();
                }
            }

            @Override
            public void dataFall() {
                loginInterface.onLoginFall();
            }
        });
    }

}
