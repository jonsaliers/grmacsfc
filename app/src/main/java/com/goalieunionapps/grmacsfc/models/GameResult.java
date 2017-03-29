package com.goalieunionapps.grmacsfc.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * A custom object for a game result
 */
@Keep
public class GameResult implements Parcelable {
    public int gameID;
    public int grmacsScore;
    public int opponentScore;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gameID);
        dest.writeInt(this.grmacsScore);
        dest.writeInt(this.opponentScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameResult that = (GameResult) o;

        if (gameID != that.gameID) {
            return false;
        }
        if (grmacsScore != that.grmacsScore) {
            return false;
        }
        if (opponentScore != that.opponentScore) {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int result = gameID;
        result = 31 * result + grmacsScore;
        result = 31 * result + opponentScore;
        return result;
    }

    public GameResult() {
    }

    protected GameResult(Parcel in) {
        this.gameID = in.readInt();
        this.grmacsScore = in.readInt();
        this.opponentScore = in.readInt();
    }

    public static final Parcelable.Creator<GameResult> CREATOR = new Parcelable.Creator<GameResult>() {
        @Override
        public GameResult createFromParcel(Parcel source) {
            return new GameResult(source);
        }

        @Override
        public GameResult[] newArray(int size) {
            return new GameResult[size];
        }
    };
}
