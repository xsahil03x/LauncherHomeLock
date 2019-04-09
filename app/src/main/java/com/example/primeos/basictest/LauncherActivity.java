package com.example.primeos.basictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeUtil.disableLauncher(LauncherActivity.this, getPackageManager());
                finish();
            }
        });
    }
}
