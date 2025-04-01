package com.example.shijianshenapp.application;

import android.app.Application;

import org.litepal.Operator;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Operator.initialize(this);
    }
}
