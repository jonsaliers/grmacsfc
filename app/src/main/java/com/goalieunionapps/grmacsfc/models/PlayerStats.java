package com.goalieunionapps.grmacsfc.models;

import android.support.annotation.Keep;

import java.util.Comparator;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */
@Keep
public class PlayerStats implements Comparable<PlayerStats> {

    public int playerID;
    public String firstName;
    public String nickName;
    public String lastName;
    public int goals;
    public int assists;
    public int gamesPlayed;
    public int yellow_cards;
    public int red_cards;

    public PlayerStats(int playerID, String firstName, String nickName, String lastName) {
        this.firstName = firstName;
        this.nickName = nickName;
        this.lastName = lastName;
        this.playerID = playerID;
    }

    @Override
    public int compareTo(PlayerStats playerStats) {

        if (goals < playerStats.goals) {
            return 1;
        } else if (goals > playerStats.goals) {
            return -1;
        }
        return lastName.compareTo(playerStats.lastName);
    }
}
