package com.goalieunionapps.grmacsfc.observables;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.Utils.HomeScreenUtils;
import com.goalieunionapps.grmacsfc.Utils.ScheduleUtils;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.GameResult;
import com.goalieunionapps.grmacsfc.models.HomeContents;
import com.goalieunionapps.grmacsfc.models.SeasonSchedule;

import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * An observable class for home screen contents
 */
public class HomeScreenObserver {

    public static Observable<HomeContents> getHomeScreen(final FirebaseDatabase firebaseDatabase,
                                                         final SeasonSchedule schedule, final Date referenceDate) {
        return Observable.create(new Observable.OnSubscribe<HomeContents>() {
            @Override
            public void call(final Subscriber<? super HomeContents> subscriber) {

                //find the next game and last game IDs
                final Game nextGame = ScheduleUtils.getGameAfterDate(referenceDate, schedule.getAllGames());
                final Game lastGame =
                        ScheduleUtils.getGameBeforeDate(referenceDate, schedule.getAllGames());

                final Query query = firebaseDatabase.getReference(Config.GAME_RESULTS).orderByChild(Config.GAME_ID);

                final ValueEventListener listener = query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        HomeContents homeContents = new HomeContents();
                        homeContents.lastGame = lastGame;
                        homeContents.nextGame = nextGame;

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            GameResult gameResult = snapshot.getValue(GameResult.class);

                            if (lastGame != null && lastGame.gameID == gameResult.gameID) {
                                homeContents.lastGame.gameResult = gameResult;
                            } else if (nextGame != null && nextGame.gameID == gameResult.gameID) {
                                homeContents.nextGame.gameResult = gameResult;
                            }

                            //determine if the game was a win or a loss
                            if (HomeScreenUtils.wasWin(gameResult)) {
                                homeContents.seasonRecord.wins++;
                            } else if (HomeScreenUtils.wasLoss(gameResult)) {
                                homeContents.seasonRecord.losses++;
                            } else if (HomeScreenUtils.wasDraw(gameResult)) {
                                homeContents.seasonRecord.draws++;
                            }

                        }

                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(homeContents);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //when the subscription is cancelled remove the listener
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        query.removeEventListener(listener);
                    }
                }));
            }
        });
    }
}
