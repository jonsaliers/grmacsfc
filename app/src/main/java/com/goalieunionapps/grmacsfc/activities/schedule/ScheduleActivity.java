package com.goalieunionapps.grmacsfc.activities.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.FirebaseDatabase;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.activities.schedule.adapters.ScheduleAdapter;
import com.goalieunionapps.grmacsfc.activities.schedule.itemdecoration.RecyclerViewDivider;
import com.goalieunionapps.grmacsfc.models.SeasonSchedule;
import com.goalieunionapps.grmacsfc.observables.ScheduleObserver;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * An activity to display the season schedule
 */
public class ScheduleActivity extends AppCompatActivity {

    Subscription soccerScheduleSubscription;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!Config.isRelease) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("CERT Schedule CERT");
        }

        recyclerView = (RecyclerView) findViewById(R.id.schedule_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ScheduleActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerViewDivider(this, R.drawable.schedule_divider));
    }

    @Override
    protected void onResume() {
        super.onResume();

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
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
                        recyclerView.setAdapter(new ScheduleAdapter(schedule));
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
}
