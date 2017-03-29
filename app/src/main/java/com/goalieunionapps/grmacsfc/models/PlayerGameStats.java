package com.goalieunionapps.grmacsfc.models;

import android.support.annotation.Keep;

import java.util.ArrayList;

/**
 * Created on 3/27/17.
 */
@Keep
public class PlayerGameStats {

    public String playerStatsKey;
    public ArrayList<Player> players;
    public GameStats playerGameStats;

    public boolean isKeyValid() {
        return playerStatsKey != null;
    }

}
