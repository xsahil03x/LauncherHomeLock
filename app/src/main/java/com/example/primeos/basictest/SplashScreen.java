package com.example.primeos.basictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeUtil.handleHomeButton(SplashScreen.this);
        finish();
    }
}
