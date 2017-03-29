package com.goalieunionapps.grmacsfc.Utils;

import android.provider.UserDictionary;

import com.goalieunionapps.grmacsfc.activities.roster.adapters.RosterListItem;
import com.goalieunionapps.grmacsfc.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonsaliers on 3/27/17.
 */

public class RosterUtils {

    public static String getFullName(Player player) {
        String playerName = "";

        if (player != null) {

            playerName = player.firstName != null ? formatName(player.firstName) : "";

            playerName += player.nickName != null ? " '" + formatName(player.nickName) + "'" : "";

            playerName += player.lastName != null ? " " + formatName(player.lastName) : "";
        }

        return playerName;
    }

    public static String getNumber(Player player) {
        String number = "??";

        if (player != null) {
            number = String.valueOf(player.number);
        }

        return number;
    }

    public static String getPosition(Player player) {
        String position = "Unknown";

        if (player != null) {
            if (player.isForward()) {
                position = "Forward";
            } else if (player.isMidfield()) {
                position = "Midfield";
            } else if (player.isDefense()) {
                position = "Defense";
            } else if (player.isGoalie()) {
                position = "Goalie";
            }
        }

        return position;
    }


    public static String formatName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static ArrayList<Player> sortRoster(List<Player> roster) {

        ArrayList<Player> sortedRoster = new ArrayList<>();
        ArrayList<Player> forwards = new ArrayList<>();
        ArrayList<Player> midfield = new ArrayList<>();
        ArrayList<Player> defense = new ArrayList<>();
        ArrayList<Player> goalie = new ArrayList<>();

        for (Player player : roster) {
            if (player.isForward()) {
                forwards.add(player);
            } else if (player.isMidfield()) {
                midfield.add(player);
            } else if (player.isDefense()) {
                defense.add(player);
            } else if (player.isGoalie()) {
                goalie.add(player);
            }
        }

        sortedRoster.addAll(forwards);
        sortedRoster.addAll(midfield);
        sortedRoster.addAll(defense);
        sortedRoster.addAll(goalie);

        return sortedRoster;
    }


}
