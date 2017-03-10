package com.test.exceptiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.ActivityManager;
import com.test.R;

public class ExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.getInstance().addActivity(this);
        int i=1/0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
