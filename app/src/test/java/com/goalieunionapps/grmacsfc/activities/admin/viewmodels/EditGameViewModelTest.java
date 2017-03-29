package com.goalieunionapps.grmacsfc.activities.admin.viewmodels;

import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.GameResult;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 3/27/17.
 */
public class EditGameViewModelTest {
    @Test
    public void getOpponentName_null() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);

        Assert.assertNotNull(editGameViewModel.getOpponentName());
    }

    @Test
    public void setOpponentName_null() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setOpponentName("test");

        Assert.assertEquals("test", editGameViewModel.getOpponentName());

        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void setOpponentName_notNull() {
        Game game = new Game();
        game.opponent = "beforeTest";

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setOpponentName("test");

        Assert.assertEquals("test", editGameViewModel.getOpponentName());

        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void setOpponentScore_null() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setOpponentScore(null);

        Assert.assertEquals("0", editGameViewModel.getOpponentScore());
        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void setOpponentScore_nullGameResult() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setOpponentScore("8");

        Assert.assertEquals("8", editGameViewModel.getOpponentScore());
        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void setOpponentScore_notNull() {
        Game game = new Game();
        game.gameResult = new GameResult();
        game.gameResult.opponentScore = 2;

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setOpponentScore("8");

        Assert.assertEquals("8", editGameViewModel.getOpponentScore());
        Assert.assertTrue(editGameViewModel.hasChanged());

    }

    @Test
    public void getOpponentScore() throws Exception {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);

        Assert.assertEquals("", editGameViewModel.getOpponentScore());
        Assert.assertFalse(editGameViewModel.hasChanged());
    }

    @Test
    public void setGRMacsScore_null() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setGRMacsScore(null);

        Assert.assertEquals("0", editGameViewModel.getGRMacsScore());
        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void setGRMacsScore_nullGameResult() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setGRMacsScore("8");

        Assert.assertEquals("8", editGameViewModel.getGRMacsScore());
        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void setGRMacsScore_notNull() {
        Game game = new Game();
        game.gameResult = new GameResult();
        game.gameResult.grmacsScore = 99;

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);
        editGameViewModel.setGRMacsScore("3");

        Assert.assertEquals("3", editGameViewModel.getGRMacsScore());
        Assert.assertTrue(editGameViewModel.hasChanged());
    }

    @Test
    public void getGRMacsScore() {
        Game game = new Game();

        EditGameViewModel editGameViewModel = new EditGameViewModel(game);

        Assert.assertEquals("", editGameViewModel.getGRMacsScore());
        Assert.assertFalse(editGameViewModel.hasChanged());
    }


}