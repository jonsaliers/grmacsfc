package com.goalieunionapps.grmacsfc.activities.admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.DragonsHockeyIntents;
import com.goalieunionapps.grmacsfc.activities.admin.adapter.AdminScheduleAdapter;
import com.goalieunionapps.grmacsfc.activities.admin.listeners.AdminClickListener;
import com.goalieunionapps.grmacsfc.activities.roster.RosterActivity;
import com.goalieunionapps.grmacsfc.activities.roster.adapters.RosterAdapter;
import com.goalieunionapps.grmacsfc.activities.roster.views.RosterHeaderDecoration;
import com.goalieunionapps.grmacsfc.activities.schedule.adapters.ScheduleAdapter;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.Player;
import com.goalieunionapps.grmacsfc.models.SeasonSchedule;
import com.goalieunionapps.grmacsfc.observables.RosterObserver;
import com.goalieunionapps.grmacsfc.observables.ScheduleObserver;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class AdminActivity extends AppCompatActivity implements AdminClickListener {

    private FirebaseDatabase firebaseDatabase;
    private RecyclerView recyclerView;
    private Subscription soccerScheduleSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (!Config.isRelease) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("CERT Admin CERT");
        }

        firebaseDatabase = FirebaseDatabase.getInstance();

        try {
            firebaseDatabase.setPersistenceEnabled(true);
        } catch (DatabaseException exception) {
            Timber.e("Unable to set persistance for Firebase");
        }

        recyclerView = (RecyclerView) findViewById(R.id.schedule_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        firebaseDatabase = FirebaseDatabase.getInstance();
        soccerScheduleSubscription = ScheduleObserver.getSoccerSchedule(firebaseDatabase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<SeasonSchedule, Observable<SeasonSchedule>>() {
                    @Override
                    public Observable<SeasonSchedule> call(SeasonSchedule schedule) {
                        return ScheduleObserver.getScheduleWithResults(firebaseDatabase, schedule);
                    }
                })
                .subscribe(new Action1<SeasonSchedule>() {
                    @Override
                    public void call(SeasonSchedule schedule) {
                        recyclerView.setAdapter(new AdminScheduleAdapter(schedule, AdminActivity.this));
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (soccerScheduleSubscription != null) {
            soccerScheduleSubscription.unsubscribe();
            soccerScheduleSubscription = null;
        }
    }

    @Override
    public void onGameClick(Game game) {
        startActivity(GRMacsFCIntents.createAdminGameIntent(this, game));
    }
}
