package com.test;

import android.app.Application;

import com.test.exceptiondemo.CustomExceptionHandler;

/**
 * Created by xiao on 2017/2/18.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CustomExceptionHandler exceptionHandler = CustomExceptionHandler.getInstance();
        exceptionHandler.init(this);
    }
}
