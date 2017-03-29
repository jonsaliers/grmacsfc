package com.goalieunionapps.grmacsfc.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 3/27/17.
 */

public class GameTest {

    @Test
    public void testEquals_falseGameID(){
        Game originalGame = new Game();

        originalGame.gameID = 2;
        originalGame.gameTime = "hi";
        originalGame.opponent = "predators";
        originalGame.gameResult = new GameResult();
        originalGame.gameResult.grmacsScore = 9;
        originalGame.gameResult.opponentScore = 7;
        originalGame.gameResult.gameID = 2;

        Game modifiedGame = new Game();
        modifiedGame.gameID = 3;
        modifiedGame.gameTime = "hi";
        modifiedGame.opponent = "predators";
        modifiedGame.gameResult = new GameResult();
        modifiedGame.gameResult.grmacsScore = 9;
        modifiedGame.gameResult.opponentScore = 7;
        modifiedGame.gameResult.gameID = 2;

        Assert.assertFalse(originalGame.equals(modifiedGame));

    }

    @Test
    public void testEquals_falseGameTime(){
        Game originalGame = new Game();

        originalGame.gameID = 2;
        originalGame.gameTime = "hi";
        originalGame.opponent = "predators";
        originalGame.gameResult = new GameResult();
        originalGame.gameResult.grmacsScore = 9;
        originalGame.gameResult.opponentScore = 7;
        originalGame.gameResult.gameID = 2;

        Game modifiedGame = new Game();
        modifiedGame.gameID = 2;
        modifiedGame.gameTime = "hi3";
        modifiedGame.opponent = "predators";
        modifiedGame.gameResult = new GameResult();
        modifiedGame.gameResult.grmacsScore = 9;
        modifiedGame.gameResult.opponentScore = 7;
        modifiedGame.gameResult.gameID = 2;

        Assert.assertFalse(originalGame.equals(modifiedGame));
    }

    @Test
    public void testEquals_falseOpponent(){
        Game originalGame = new Game();

        originalGame.gameID = 2;
        originalGame.gameTime = "hi";
        originalGame.opponent = "predators";
        originalGame.gameResult = new GameResult();
        originalGame.gameResult.grmacsScore = 9;
        originalGame.gameResult.opponentScore = 7;
        originalGame.gameResult.gameID = 2;

        Game modifiedGame = new Game();
        modifiedGame.gameID = 2;
        modifiedGame.gameTime = "hi";
        modifiedGame.opponent = "wings";
        modifiedGame.gameResult = new GameResult();
        modifiedGame.gameResult.grmacsScore = 9;
        modifiedGame.gameResult.opponentScore = 7;
        modifiedGame.gameResult.gameID = 2;

        Assert.assertFalse(originalGame.equals(modifiedGame));
    }

    @Test
    public void testEquals_falseGRMacs(){
        Game originalGame = new Game();

        originalGame.gameID = 2;
        originalGame.gameTime = "hi";
        originalGame.opponent = "predators";
        originalGame.gameResult = new GameResult();
        originalGame.gameResult.grmacsScore = 9;
        originalGame.gameResult.opponentScore = 7;
        originalGame.gameResult.gameID = 2;

        Game modifiedGame = new Game();
        modifiedGame.gameID = 2;
        modifiedGame.gameTime = "hi";
        modifiedGame.opponent = "predators";
        modifiedGame.gameResult = new GameResult();
        modifiedGame.gameResult.grmacsScore = 10;
        modifiedGame.gameResult.opponentScore = 7;
        modifiedGame.gameResult.gameID = 2;

        Assert.assertFalse(originalGame.equals(modifiedGame));
    }

    @Test
    public void testEquals_falseOpponentScore(){
        Game originalGame = new Game();

        originalGame.gameID = 2;
        originalGame.gameTime = "hi";
        originalGame.opponent = "predators";
        originalGame.gameResult = new GameResult();
        originalGame.gameResult.grmacsScore = 9;
        originalGame.gameResult.opponentScore = 7;
        originalGame.gameResult.gameID = 2;

        Game modifiedGame = new Game();
        modifiedGame.gameID = 2;
        modifiedGame.gameTime = "hi";
        modifiedGame.opponent = "predators";
        modifiedGame.gameResult = new GameResult();
        modifiedGame.gameResult.grmacsScore = 9;
        modifiedGame.gameResult.opponentScore = 6;
        modifiedGame.gameResult.gameID = 2;

        Assert.assertFalse(originalGame.equals(modifiedGame));
    }

    @Test
    public void testEquals_true(){
        Game originalGame = new Game();

        originalGame.gameID = 2;
        originalGame.gameTime = "hi";
        originalGame.opponent = "predators";
        originalGame.gameResult = new GameResult();
        originalGame.gameResult.grmacsScore = 9;
        originalGame.gameResult.opponentScore = 7;
        originalGame.gameResult.gameID = 2;

        Game modifiedGame = new Game();
        modifiedGame.gameID = 2;
        modifiedGame.gameTime = "hi";
        modifiedGame.opponent = "predators";
        modifiedGame.gameResult = new GameResult();
        modifiedGame.gameResult.grmacsScore = 9;
        modifiedGame.gameResult.opponentScore = 7;
        modifiedGame.gameResult.gameID = 2;

        Assert.assertTrue(originalGame.equals(modifiedGame));
    }
}
