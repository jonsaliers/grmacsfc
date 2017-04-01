package com.goalieunionapps.grmacsfc.observables;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.Player;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class RosterObserver {

    public static Observable<List<Player>> GetRoster(final FirebaseDatabase database) {
        return Observable.create(new Observable.OnSubscribe<List<Player>>() {
            @Override
            public void call(final Subscriber<? super List<Player>> subscriber) {

                final Query query = database.getReference(Config.ROSTER).orderByChild(Player.PLAYER_NUMBER);
                final ValueEventListener listener = query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Player> roster = new ArrayList<Player>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            roster.add(snapshot.getValue(Player.class));
                        }

                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(roster);
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
}
