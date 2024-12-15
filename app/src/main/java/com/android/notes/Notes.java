package com.android.notes;

import android.app.Application;

import com.android.notes.di.AppComponent;
import com.android.notes.di.DaggerAppComponent;

public class Notes extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(this);
    }
}