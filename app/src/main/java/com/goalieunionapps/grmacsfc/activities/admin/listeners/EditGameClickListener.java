package com.goalieunionapps.grmacsfc.activities.admin.listeners;

import java.util.Date;

/**
 * Created on 3/27/17.
 */

public interface EditGameClickListener {

    void onDateClick(Date gameDate);

    void onTimeClick(Date gameDate);

    void onEditStatsClick();

    void onClearGameClick();
}
