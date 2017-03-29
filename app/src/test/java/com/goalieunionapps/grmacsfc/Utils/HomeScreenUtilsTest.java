package com.goalieunionapps.grmacsfc.Utils;

import com.goalieunionapps.grmacsfc.models.GameResult;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by jonsaliers on 3/27/16.
 */

public class HomeScreenUtilsTest {

    @Test
    public void wasGameWin_Test(){

        Assert.assertFalse("Null test", HomeScreenUtils.wasWin(null));

        GameResult gameResult = new GameResult();
        gameResult.grmacsScore = 0;
        gameResult.opponentScore = 1;

        Assert.assertFalse("Loss test", HomeScreenUtils.wasWin(gameResult));

        gameResult.grmacsScore = 2;

        Assert.assertTrue("Win test", HomeScreenUtils.wasWin(gameResult));
    }

    @Test
    public void wasGameLoss_Test(){
        Assert.assertFalse("Null test", HomeScreenUtils.wasLoss(null));

        GameResult gameResult = new GameResult();
        gameResult.grmacsScore = 0;
        gameResult.opponentScore = 1;

        Assert.assertTrue("Loss test", HomeScreenUtils.wasLoss(gameResult));

        gameResult.grmacsScore = 2;

        Assert.assertFalse("Win test", HomeScreenUtils.wasLoss(gameResult));
    }

    @Test
    public void wasGameDraw_Test(){

        Assert.assertFalse("Null test", HomeScreenUtils.wasDraw(null));

        GameResult gameResult = new GameResult();
        gameResult.grmacsScore = 0;
        gameResult.opponentScore = 1;

        Assert.assertFalse("Loss test", HomeScreenUtils.wasDraw(gameResult));

        gameResult.grmacsScore = 2;

        Assert.assertFalse("Win test", HomeScreenUtils.wasDraw(gameResult));

        gameResult.grmacsScore = 1;

        Assert.assertTrue("draw test", HomeScreenUtils.wasDraw(gameResult));
    }
}
