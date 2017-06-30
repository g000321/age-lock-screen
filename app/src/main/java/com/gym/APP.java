package com.gym;

import android.app.Application;
import android.content.Context;


public class APP extends Application {
    public static Context appContext;
    private static APP instance;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        instance = this;

    }

    public static APP getInstance() {
        return instance;
    }

}
