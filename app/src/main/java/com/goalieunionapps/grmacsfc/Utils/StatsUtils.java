package com.goalieunionapps.grmacsfc.Utils;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.goalieunionapps.grmacsfc.models.Player;
import com.goalieunionapps.grmacsfc.models.PlayerStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jonsaliers on 3/27/17.
 */

public class StatsUtils {

    public static ArrayList<PlayerStats> toPlayerStats(@NonNull SparseArray<PlayerStats> sparseArray) {


        ArrayList<PlayerStats> statsArrayList = new ArrayList<>();

        for (int i = 0; i < sparseArray.size(); i++) {
            statsArrayList.add(sparseArray.valueAt(i));
        }

        return statsArrayList;
    }

    public static String fullPlayerName(@NonNull PlayerStats playerStats) {

        String name = playerStats.firstName != null ? playerStats.firstName : "";

        name += playerStats.nickName != null ? " '" + playerStats.nickName + "'" : "";

        name += playerStats.lastName != null ? " " + playerStats.lastName : "";

        return name;
    }

}
