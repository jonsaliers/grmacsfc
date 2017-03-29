package com.goalieunionapps.grmacsfc;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by jonsaliers on 3/27/17.
 */

public class DragonsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
