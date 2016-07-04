package com.oneguy.live;

import android.app.Application;
import android.content.Context;


public class App extends Application {
    private static App instance;
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    protected Context getContext() {
        return getApplicationContext();
    }

    public static App getInstance() {
        return instance;
    }


}
