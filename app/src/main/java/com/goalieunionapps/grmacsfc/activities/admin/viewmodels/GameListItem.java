package com.goalieunionapps.grmacsfc.activities.admin.viewmodels;

import android.support.annotation.NonNull;

import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.DateFormaters;
import com.goalieunionapps.grmacsfc.Utils.FormattingUtils;
import com.goalieunionapps.grmacsfc.models.Game;

/**
 * Created on 3/27/17.
 */

public class GameListItem extends ListItem {

    private Game game;

    public GameListItem(@NonNull Game game) {
        super(R.layout.view_admin_game_card);
        this.game = game;
    }

    public String getGameID() {
        return String.valueOf(game.gameID);
    }

    public String getGameDate() {
        return DateFormaters.getGameTime(game.gameTimeToDate());
    }

    public String getGameOpponent() {
        return game.opponent;
    }

    public String getGameResult() {
        return FormattingUtils.getGameScore(game.gameResult, game.opponent);
    }

    public Game getGame() {
        return game;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.GAME;
    }
}
