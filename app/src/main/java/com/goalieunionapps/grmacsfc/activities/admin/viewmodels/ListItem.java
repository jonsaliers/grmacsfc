package com.goalieunionapps.grmacsfc.activities.admin.viewmodels;

/**
 * Created on 3/27/17.
 */

public abstract class ListItem {

    private int layoutID;

    public abstract ItemType getItemType();


    public ListItem(int layoutID) {
        this.layoutID = layoutID;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public enum ItemType {
        GAME,
        ADD_GAME
    }
}
