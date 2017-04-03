package com.goalieunionapps.grmacsfc.Utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goalieunionapps.grmacsfc.models.GameResult;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class HomeScreenUtils {
    public static boolean wasWin(@Nullable GameResult gameResult) {
        if (gameResult != null) {
            return gameResult.grmacsScore > gameResult.opponentScore;
        }

        return false;
    }

    public static boolean wasLoss(@Nullable GameResult gameResult) {
        if (gameResult != null ) {
            return gameResult.grmacsScore < gameResult.opponentScore;
        }

        return false;
    }

    public static boolean wasDraw(@Nullable GameResult gameResult) {
        return gameResult != null && gameResult.grmacsScore == gameResult.opponentScore;
    }

}
