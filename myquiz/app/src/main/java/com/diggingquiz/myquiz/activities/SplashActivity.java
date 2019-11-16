package com.diggingquiz.myquiz.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;


public class SplashActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SplashActivity.this;
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PrefManager prefManager=new PrefManager(SplashActivity.this);
                if(prefManager.isFirstTime()){
                    startActivity(new Intent(getApplicationContext(), HomeMainActivity.class));
                    finish();

                }else if(!prefManager.isFirstTime()) {
                    startActivity(new Intent(getApplicationContext(),UserLoginActivity.class));
                    finish();
                }


            }
        },3000);


    }
}
