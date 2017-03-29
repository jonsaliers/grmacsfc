package com.goalieunionapps.grmacsfc.activities.home;


import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.View;

import com.goalieunionapps.grmacsfc.BR;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.DateFormaters;
import com.goalieunionapps.grmacsfc.Utils.FormattingUtils;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.HomeContents;

import java.util.Calendar;
import java.util.Date;

@Keep
public class HomeScreenViewModel extends BaseObservable{
    private HomeContents homeContents;
    private boolean isDataReady;

    public HomeScreenViewModel(@Nullable HomeContents homeContents){
        if(homeContents == null){
            this.homeContents = new HomeContents();
            setDataReady(true);
        }else {
            this.homeContents = homeContents;
            setDataReady(false);
        }
    }

    @Bindable
    public boolean isDataReady(){
        return isDataReady;
    }

    public void setDataReady(boolean dataReady){
        isDataReady = true;
        notifyPropertyChanged(BR.dataReady);
    }


    public String getNextGameTime(Context context){
        if (homeContents.nextGame == null) {
            return context.getString(R.string.no_more_games);
        }

        Date date = homeContents.nextGame.gameTimeToDate();

        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            if (DateUtils.isToday(date.getTime())) {

                return context.getString(R.string.today_gameday,
                        DateFormaters.getGameTime(date),
                        homeContents.nextGame.home?"Home":"Guest");

            } else {
                return context.getString(R.string.gameday,
                        DateFormaters.getGameDateTime(date),
                        homeContents.nextGame.home?"Home":"Guest");
            }
        }

        return context.getString(R.string.no_more_games);
    }

    public String getLastGameResult(Context context){
        if (homeContents.lastGame != null && homeContents.lastGame.gameResult != null) {

            final Game lastGame = homeContents.lastGame;
            String gameResultString = FormattingUtils.getGameResultAsString(homeContents.lastGame.gameResult);
            return  String.format(context.getString(R.string.last_game_score), lastGame.gameResult.grmacsScore,
                            lastGame.opponent, lastGame.gameResult.opponentScore, gameResultString);

        } else if (homeContents.lastGame != null) {
            return context.getString(R.string.update_pending);
        } else{
            return "";
        }
    }

    public int showLastGameInfo(){
        return homeContents.lastGame == null ? View.INVISIBLE : View.VISIBLE;
    }

    public String getWins(){
        if (homeContents.seasonRecord == null) {
           return "";
        } else {
            return String.valueOf(homeContents.seasonRecord.wins);
        }
    }

    public String getLosses(){
        if (homeContents.seasonRecord == null) {
            return "";
        } else {
            return String.valueOf(homeContents.seasonRecord.losses);
        }
    }

    public String getDraws(){
        if (homeContents.seasonRecord == null) {
            return "";
        } else {
            return String.valueOf(homeContents.seasonRecord.draws);
        }
    }
}
