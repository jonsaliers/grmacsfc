package com.goalieunionapps.grmacsfc;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class GRMacsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
