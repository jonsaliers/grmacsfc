package com.goalieunionapps.grmacsfc.models;

import android.support.annotation.Keep;

/**
 * A player object.
 */
@Keep
public class Player {

    public static final String FORWARD = "F";
    public static final String MIDFIELD = "M";
    public static final String DEFENSE = "D";
    public static final String GOALIE = "G";
    public static final String PLAYER_NUMBER = "number";

    public String firstName;
    public String nickName;
    public String lastName;
    public int number;
    public int playerID;
    public String position;
    public boolean injuredReserved;

    public Player() {
        //default constructor
    }

    public Player(String position) {
        this.position = position;
    }


    public boolean isForward() {
        return FORWARD.equalsIgnoreCase(position);
    }

    public boolean isMidfield() {
        return MIDFIELD.equalsIgnoreCase(position);
    }

    public boolean isDefense() {
        return DEFENSE.equalsIgnoreCase(position);
    }

    public boolean isGoalie() {
        return GOALIE.equalsIgnoreCase(position);
    }
}
