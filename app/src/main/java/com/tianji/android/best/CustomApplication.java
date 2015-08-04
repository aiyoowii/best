package com.tianji.android.best;

import android.app.Application;

public class CustomApplication extends Application {

    public static CustomApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
