package com.goalieunionapps.grmacsfc.activities.admin.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;

//import com.android.annotations.Nullable;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.DateFormaters;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.GameResult;

import java.util.Date;
import java.util.Locale;

/**
 * Created on 3/27/17.
 */

public class EditGameViewModel {
    private Game game;
    private Game originalGame;

    public EditGameViewModel(@NonNull Game game) {
        this.game = game;

        try {
            originalGame = game.clone();
        } catch (CloneNotSupportedException e) {
            originalGame = new Game();
        }
    }

    public String getGameID(Context context) {
        return context.getString(R.string.edit_game_game_id, game.gameID);
    }

    public String getOpponentName() {
        return game.opponent == null ? "" : game.opponent;
    }

    public void setOpponentName(String name) {
        game.opponent = name;
    }

    public void setOpponentScore(String score) {
        if (game.gameResult == null) {
            createGameResult();
        }

        if (score == null || score.isEmpty()) {
            game.gameResult.opponentScore = 0;
        } else {
            game.gameResult.opponentScore = Integer.valueOf(score);
        }
    }

    public String getOpponentScore() {
        if (game.gameResult != null) {
            return String.valueOf(game.gameResult.opponentScore);
        } else {
            return "";
        }
    }

    public void setGRMacsScore(String score) {
        if (game.gameResult == null) {
            createGameResult();
        }

        if (score == null || score.isEmpty()) {
            game.gameResult.grmacsScore = 0;
        } else {
            game.gameResult.grmacsScore = Integer.valueOf(score);
        }
    }

    public String getGRMacsScore() {
        if (game.gameResult != null) {
            return String.valueOf(game.gameResult.grmacsScore);
        } else {
            return "";
        }
    }

    public Date getGameDate() {
        return game.gameTimeToDate();
    }

    public void setGameDate(Date gameDate) {
        game.gameTime = DateFormaters.convertDateToGameTime(gameDate);
    }

    public String getGameDateAsString() {
        return DateFormaters.getGameDate(game.gameTimeToDate());
    }

    public String getGameTimeAsString() {
        return DateFormaters.getGameTime(game.gameTimeToDate());
    }

    public boolean hasChanged() {
        return !originalGame.equals(game);
    }

    public Game getGame() {
        return game;
    }

    private void createGameResult() {
        game.gameResult = new GameResult();
        game.gameResult.gameID = originalGame.gameID;
    }
}
