package com.goalieunionapps.grmacsfc.models;

import android.support.annotation.Keep;

/**
 * A custom object for the home screen contents
 */
@Keep
public class HomeContents {

    public Game lastGame;
    public Game nextGame;
    public SeasonRecord seasonRecord;

    public HomeContents() {
        seasonRecord = new SeasonRecord();
    }
}
