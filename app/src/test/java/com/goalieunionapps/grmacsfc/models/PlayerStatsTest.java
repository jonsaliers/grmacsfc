package com.goalieunionapps.grmacsfc.models;

import com.goalieunionapps.grmacsfc.models.Player;
import com.goalieunionapps.grmacsfc.models.PlayerStats;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class PlayerStatsTest {

    @Test
    public void testSortPoints(){

        ArrayList<PlayerStats> playerStats = new ArrayList<>();

        PlayerStats stats = new PlayerStats(1, "bob", "will", "joe");
        stats.goals = 4;
        playerStats.add(stats);

        stats = new PlayerStats(1, "jeff", "jon", "france");
        stats.goals = 5;
        playerStats.add(stats);

        stats = new PlayerStats(1, "dan", "shaun", "wham");
        stats.goals = 2;
        playerStats.add(stats);

        stats = new PlayerStats(1, "will", "holly", "zaon");
        stats.goals = 6;
        playerStats.add(stats);

        Collections.sort(playerStats);

        Assert.assertEquals("zaon", playerStats.get(0).lastName);
        Assert.assertEquals("france", playerStats.get(1).lastName);
        Assert.assertEquals("joe", playerStats.get(2).lastName);
        Assert.assertEquals("wham", playerStats.get(3).lastName);
    }

    @Test
    public void testSortName(){

        ArrayList<PlayerStats> playerStats = new ArrayList<>();

        PlayerStats stats = new PlayerStats(1, "bb", "bb", "bb");
        stats.goals = 4;
        playerStats.add(stats);

        stats = new PlayerStats(1, "aa", "aa", "aa");
        stats.goals = 4;
        playerStats.add(stats);

        stats = new PlayerStats(1, "aaa", "aaa", "aaa");
        stats.goals = 3;
        playerStats.add(stats);

        Collections.sort(playerStats);

        Assert.assertEquals("aa", playerStats.get(0).lastName);
        Assert.assertEquals("bb", playerStats.get(1).lastName);
        Assert.assertEquals("aaa", playerStats.get(2).lastName);

    }
}
