package com.goalieunionapps.grmacsfc.Utils;

import android.support.annotation.Nullable;

import com.goalieunionapps.grmacsfc.models.Game;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class ScheduleUtils {
    /**
     * Gets the game before the date given.
     *
     * @param date                       Date to get game before
     * @param gamesSortedChronologically A sorted list of games (chronologically sorted)
     * @return The game before the date in the list or null
     */
    @Nullable
    public static Game getGameBeforeDate(Date date, List<Game> gamesSortedChronologically) {

        if (gamesSortedChronologically == null || gamesSortedChronologically.size() == 0) {
            return null;
        }

        Game foundGame = null;

        for (Game game : gamesSortedChronologically) {
            Date gameDate = game.gameTimeToDate();

            if (gameDate != null && gameDate.before(date)) {
                foundGame = game;
            } else if (gameDate != null && gameDate.after(date)) {
                break;//we have our game in the list exit the loop
            }
        }

        return foundGame;
    }

    public static Game getGameAfterDate(Date date, List<Game> gamesSortedChronologically) {

        if (gamesSortedChronologically == null || gamesSortedChronologically.size() == 0) {
            return null;
        }

        Game foundGame = null;

        for (Game game : gamesSortedChronologically) {
            Date gameDate = game.gameTimeToDate();

            if (gameDate != null && gameDate.after(date)) {
                foundGame = game;
                break;
            }
        }

        return foundGame;
    }
}
