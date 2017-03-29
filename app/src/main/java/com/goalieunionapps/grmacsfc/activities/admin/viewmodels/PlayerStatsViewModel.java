package com.goalieunionapps.grmacsfc.activities.admin.viewmodels;

import com.goalieunionapps.grmacsfc.models.GameStats;
import com.goalieunionapps.grmacsfc.models.PlayerStats;

/**
 * Created on 3/27/17.
 */

public class PlayerStatsViewModel {

    private String playerName;
    private int goals, assists, yellowCards, redCards, playerID, playerNumber;
    private boolean isPresent, isDirty;


    public static class PlayerStatsVMBuilder {
        private String playerName;
        private int goals, assists, yellowCards, redCards, playerID, playerNumber;
        private boolean isPresent;

        public PlayerStatsVMBuilder() {

        }

        public PlayerStatsVMBuilder playerID(int playerID) {
            this.playerID = playerID;
            return this;
        }

        public PlayerStatsVMBuilder playerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public PlayerStatsVMBuilder goals(int goals) {
            this.goals = goals;
            return this;
        }

        public PlayerStatsVMBuilder assists(int assists) {
            this.assists = assists;
            return this;
        }

        public PlayerStatsVMBuilder yellowCards(int yellowCards) {
            this.yellowCards = yellowCards;
            return this;
        }

        public PlayerStatsVMBuilder redCards(int redCards) {
            this.redCards = redCards;
            return this;
        }

        public PlayerStatsVMBuilder playerNumber(int playerNumber) {
            this.playerNumber = playerNumber;
            return this;
        }

        public PlayerStatsVMBuilder present(boolean present) {
            this.isPresent = present;
            return this;
        }

        public PlayerStatsViewModel build() {
            return new PlayerStatsViewModel(this.playerName, this.goals, this.assists, this.yellowCards, this.redCards, this.playerID, this.playerNumber, this.isPresent);
        }
    }


    public PlayerStatsViewModel(String playerName, int goals, int assists, int yellowCards, int redCards,
                                int playerID, int playerNumber, boolean isPresent) {
        this.playerID = playerID;
        this.assists = assists;
        this.goals = goals;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.isPresent = isPresent;
        isDirty = false;
    }

    public String getPlayerName() {
        return playerName != null ? playerName : "";
    }

    public String getPlayerNumber() {
        return String.valueOf(playerNumber);
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean getPresence() {
        return isPresent;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setPresence(boolean isPresent) {
        this.isPresent = isPresent;
        isDirty = true;
    }

    public String getGoals() {
        return String.valueOf(goals);
    }

    public void setGoals(String goals) {
        if (goals == null || goals.isEmpty()) {
            this.goals = 0;
        } else {
            this.goals = Integer.valueOf(goals);
        }

        isDirty = true;
    }

    public String getAssists() {
        return String.valueOf(assists);
    }

    public void setAssists(String assists) {
        if (assists == null || assists.isEmpty()) {
            this.assists = 0;
        } else {
            this.assists = Integer.valueOf(assists);
        }

        isDirty = true;
    }

    public String getYellowCards() {
        return String.valueOf(yellowCards);
    }

    public void setYellowCards(String yellowCards) {
        if (yellowCards == null || yellowCards.isEmpty()) {
            this.yellowCards = 0;
        } else {
            this.yellowCards = Integer.valueOf(yellowCards);
        }

        isDirty = true;
    }

    public String getRedCards() {
        return String.valueOf(redCards);
    }

    public void setRedCards(String redCards) {
        if (redCards == null || redCards.isEmpty()) {
            this.redCards = 0;
        } else {
            this.redCards = Integer.valueOf(redCards);
        }

        isDirty = true;
    }

}
