package com.goalieunionapps.grmacsfc.observables;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.Utils.ScheduleUtils;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.GameResult;
import com.goalieunionapps.grmacsfc.models.SeasonSchedule;
import com.goalieunionapps.grmacsfc.models.HomeContents;

import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * A custom class to help with observable code.
 */
public class ScheduleObserver {
    public static Observable<SeasonSchedule> getSoccerSchedule(
            final FirebaseDatabase firebaseDatabase) {
        return Observable.create(new Observable.OnSubscribe<SeasonSchedule>() {
            @Override
            public void call(final Subscriber<? super SeasonSchedule> subscriber) {
                final Query query = firebaseDatabase.getReference(Config.GAMES).orderByChild("gameID");

                final ValueEventListener listener = query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        SeasonSchedule schedule = new SeasonSchedule();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            schedule.addGame(snapshot.getValue(Game.class));
                        }

                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(schedule);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("tag", "Error retrieving data");
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


    public static Observable<SeasonSchedule> getScheduleWithResults(
            final FirebaseDatabase firebaseDatabase, final SeasonSchedule schedule) {
        return Observable.create(new Observable.OnSubscribe<SeasonSchedule>() {
            @Override
            public void call(final Subscriber<? super SeasonSchedule> subscriber) {
                final Query query = firebaseDatabase.getReference(Config.GAME_RESULTS).orderByChild("gameID");

                final ValueEventListener listener = query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            GameResult gameResult = snapshot.getValue(GameResult.class);

                            if (gameResult != null) {
                                Game game = schedule.getGame(gameResult.gameID);

                                if (game != null) {
                                    game.gameResult = gameResult;
                                }
                            }
                        }

                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(schedule);
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
