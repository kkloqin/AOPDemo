package com.syy.study;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG = "LogAspect_" + MyApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
