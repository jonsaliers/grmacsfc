package com.goalieunionapps.grmacsfc.Utils;

import android.content.Context;
import android.content.Intent;

import com.goalieunionapps.grmacsfc.activities.admin.EditGameActivity;
import com.goalieunionapps.grmacsfc.activities.admin.AdminActivity;
import com.goalieunionapps.grmacsfc.activities.admin.AdminAuthActivity;
import com.goalieunionapps.grmacsfc.activities.admin.EditStatsActivity;
import com.goalieunionapps.grmacsfc.activities.roster.RosterActivity;
import com.goalieunionapps.grmacsfc.activities.schedule.ScheduleActivity;
import com.goalieunionapps.grmacsfc.activities.stats.StatsActivity;
import com.goalieunionapps.grmacsfc.models.Game;


public class GRMacsFCIntents {

    public static final String EXTRA_GAME = "com.goalieunionapps.grmacsfc.extragame";
    public static final String EXTRA_GAME_ID = "com.goalieunionapps.grmacsfc.extragameid";

    public static Intent createRosterIntent(Context context) {
        return new Intent(context, RosterActivity.class);
    }

    public static Intent createStatsIntent(Context context) {
        return new Intent(context, StatsActivity.class);
    }

    public static Intent createScheduleIntent(Context context) {
        return new Intent(context, ScheduleActivity.class);
    }

    public static Intent createAdminAuthIntent(Context context) {
        return new Intent(context, AdminAuthActivity.class);
    }

    public static Intent createAdminIntent(Context context) {
        return new Intent(context, AdminActivity.class);
    }

    public static Intent createAdminGameIntent(Context context, Game game) {
        Intent intent = new Intent(context, EditGameActivity.class);

        intent.putExtra(EXTRA_GAME, game);

        return intent;
    }

    public static Intent createEditGameStatsIntent(Context context, int gameID) {
        Intent intent = new Intent(context, EditStatsActivity.class);

        intent.putExtra(EXTRA_GAME_ID, gameID);

        return intent;
    }

}
