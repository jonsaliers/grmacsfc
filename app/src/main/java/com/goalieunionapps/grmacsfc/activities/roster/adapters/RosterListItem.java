package com.goalieunionapps.grmacsfc.activities.roster.adapters;

import com.goalieunionapps.grmacsfc.models.Player;

/**
 * Created by jonsaliers on 3/27/17.
 */

public class RosterListItem {
    public static final int HEADER_TYPE = 1;
    public static final int ROSTER_TYPE = 2;

    public Player player;
    public final int rosterItemType;

    public RosterListItem(Player player) {
        rosterItemType = ROSTER_TYPE;
        this.player = player;
    }

    public RosterListItem() {
        rosterItemType = HEADER_TYPE;
    }

}
