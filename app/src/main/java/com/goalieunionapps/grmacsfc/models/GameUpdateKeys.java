package com.goalieunionapps.grmacsfc.models;

import android.support.annotation.Keep;

import com.goalieunionapps.grmacsfc.observables.AdminObserver;

/**
 * Created on 3/27/17.
 */
@Keep
public class GameUpdateKeys {

    String gameResultKey;
    String gameKey;
    String gameStatsKey;

    public GameUpdateKeys(String gameKey, String gameResultKey, String gameStatsKey) {
        this.gameKey = gameKey;
        this.gameResultKey = gameResultKey;
        this.gameStatsKey = gameStatsKey;
    }

    public String getGameResultKey() {
        return String.valueOf(gameResultKey);
    }

    public String getGameKey() {
        return String.valueOf(gameKey);
    }

    public String getGameStatsKey() {
        return String.valueOf(gameStatsKey);
    }

    public boolean gameResultKeyValid() {
        return gameResultKey != null;
    }

    public boolean gameKeyValid() {
        return gameKey != null;
    }

    public boolean gameStatsKeyValid() {
        return gameStatsKey != null;
    }
}
