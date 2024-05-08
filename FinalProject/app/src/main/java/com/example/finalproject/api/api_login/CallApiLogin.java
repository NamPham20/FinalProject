package com.example.finalproject.api.api_login;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.finalproject.module.Account;
import com.example.finalproject.sharePreference.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiLogin {

    Account account;
    public void getUserByUserName(String userName, OnListenSuccessInterface onListenSuccessInterface){
        System.out.println("getUserByUserName is called");
        APILogin.api.getAccountByUserName(userName).enqueue(new Callback<Account>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                account = response.body();
                if(account!=null){
                    DataLocalManager.saveAccount(account);
                    System.out.println("dataOk is called");
                    onListenSuccessInterface.dataOk();
                }
                else
                    onListenSuccessInterface.dataFall();
                    System.out.println("dataFall is called");
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("onFailure is called");
                t.printStackTrace();
            }
        });
    }
}
