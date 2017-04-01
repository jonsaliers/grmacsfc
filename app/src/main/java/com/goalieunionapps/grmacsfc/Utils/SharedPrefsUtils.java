package com.goalieunionapps.grmacsfc.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.goalieunionapps.grmacsfc.Config;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class SharedPrefsUtils {

    private static final String LOGGED_IN = "com.goalieunionapps.grmacsfc.isLoggedIn";


    public static void clearPrefs(Context context) {
        SharedPreferences.Editor sharedPreferencesEdit = getAppPrefsEditor(context);

        sharedPreferencesEdit.clear();
        sharedPreferencesEdit.apply();
    }

    private static SharedPreferences getAppSharedPrefs(Context context) {
        return context.getSharedPreferences(Config.MAIN_PREFS, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getAppPrefsEditor(Context context) {
        return getAppSharedPrefs(context).edit();
    }
}
