package com.goalieunionapps.grmacsfc.models;

import android.support.annotation.Keep;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.util.List;

/**
 * Created by jonsaliers on 3/27/17.
 */
@Keep
public class GameStats {

    @Exclude
    public String key;

    public int gameID;

    @PropertyName("stats")
    public List<Stats> gameStats;

    @Keep
    public static class Stats {

        public int playerID;
        public int assists;
        public int goals;
        public int yellow_cards;
        public int red_cards;
        public boolean present;

    }

    public Stats getPlayerStats(int playerID) {
        if (gameStats != null) {
            for (Stats playerStats : gameStats) {
                if (playerStats.playerID == playerID) {
                    return playerStats;
                }
            }
        }

        return new Stats();
    }


}
