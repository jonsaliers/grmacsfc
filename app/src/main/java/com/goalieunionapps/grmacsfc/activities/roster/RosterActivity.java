package com.goalieunionapps.grmacsfc.activities.roster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.activities.roster.adapters.RosterAdapter;
import com.goalieunionapps.grmacsfc.activities.roster.views.RosterHeaderDecoration;
import com.goalieunionapps.grmacsfc.models.Player;
import com.goalieunionapps.grmacsfc.observables.RosterObserver;

import java.util.List;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */
public class RosterActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private Subscription rosterSubscription;
    private RecyclerView recyclerView;
    private TextView rosterUnavailable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_roster);

        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!Config.isRelease) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("CERT Roster CERT");
        }

        rosterUnavailable = ButterKnife.findById(this, R.id.roster_unavailable);

        firebaseDatabase = FirebaseDatabase.getInstance();

        try {
            firebaseDatabase.setPersistenceEnabled(true);
        } catch (DatabaseException exception) {
            Timber.e("Unable to set persistance for Firebase");
        }

        recyclerView = (RecyclerView) findViewById(R.id.roster_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onResume() {
        super.onResume();

        rosterSubscription = RosterObserver.GetRoster(firebaseDatabase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Timber.e("Unable to get the roster...");
                        rosterUnavailable.animate().alpha(1f);
                    }
                })
                .subscribe(new Action1<List<Player>>() {
                    @Override
                    public void call(List<Player> players) {
                        rosterUnavailable.setAlpha(0);
                        RosterAdapter adapter = new RosterAdapter(RosterActivity.this, players, recyclerView);
                        recyclerView.setAdapter(adapter);

                        recyclerView.addItemDecoration(new RosterHeaderDecoration(adapter, recyclerView));
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (rosterSubscription != null) {
            rosterSubscription.unsubscribe();
            rosterSubscription = null;
        }
    }
}
