package com.goalieunionapps.grmacsfc.activities.admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.goalieunionapps.grmacsfc.Config;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.GRMacsFCIntents;
import com.goalieunionapps.grmacsfc.Utils.RosterUtils;
import com.goalieunionapps.grmacsfc.activities.admin.adapter.AdminEditsStatsAdapter;
import com.goalieunionapps.grmacsfc.activities.admin.viewmodels.PlayerStatsViewModel;
import com.goalieunionapps.grmacsfc.models.GameStats;
import com.goalieunionapps.grmacsfc.models.Player;
import com.goalieunionapps.grmacsfc.models.PlayerGameStats;
import com.goalieunionapps.grmacsfc.observables.AdminObserver;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class EditStatsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Subscription subscription;
    private FirebaseDatabase firebaseDatabase;
    private PlayerGameStats playerGameStats;
    private AdminEditsStatsAdapter adminEditsStatsAdapter;
    private int gameID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stats_auth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.admin_stats_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();

        gameID = getIntent().getIntExtra(GRMacsFCIntents.EXTRA_GAME_ID, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adminEditsStatsAdapter == null) {
            subscription = AdminObserver.getPlayerStatsForGame(firebaseDatabase, gameID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<PlayerGameStats>() {
                        @Override
                        public void call(PlayerGameStats stats) {
                            playerGameStats = stats;

                            adminEditsStatsAdapter = new AdminEditsStatsAdapter(getViewModel());
                            recyclerView.setAdapter(adminEditsStatsAdapter);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            new AlertDialog.Builder(EditStatsActivity.this).setMessage("Unable to retrieve stats information")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    });
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (adminEditsStatsAdapter.statsChanged()) {

            ArrayList<PlayerStatsViewModel> viewModel = adminEditsStatsAdapter.getStats();

            if (playerGameStats.isKeyValid()) {
                firebaseDatabase.getReference().child(Config.GAME_STATS)
                        .child(playerGameStats.playerStatsKey)
                        .setValue(getGameStats(viewModel));
            } else {
                DatabaseReference newGameResultRef = firebaseDatabase.getReference().child(Config.GAME_STATS).push();
                newGameResultRef.setValue(getGameStats(viewModel));
            }
        }
    }

    private ArrayList<PlayerStatsViewModel> getViewModel() {
        ArrayList<PlayerStatsViewModel> statsViewModel = new ArrayList<PlayerStatsViewModel>();

        for (Player player : playerGameStats.players) {

            GameStats.Stats statsForPlayer = playerGameStats.playerGameStats.getPlayerStats(player.playerID);

            if (statsForPlayer == null) {
                statsForPlayer = new GameStats.Stats();
            }

            PlayerStatsViewModel viewModel = new PlayerStatsViewModel.PlayerStatsVMBuilder()
                    .playerName(RosterUtils.getFullName(player))
                    .playerID(player.playerID)
                    .playerNumber(player.number)
                    .goals(statsForPlayer.goals)
                    .assists(statsForPlayer.assists)
                    .yellowCards(statsForPlayer.yellow_cards)
                    .redCards(statsForPlayer.red_cards)
                    .present(statsForPlayer.present)
                    .build();

            statsViewModel.add(viewModel);
        }

        return statsViewModel;

    }

    private GameStats getGameStats(List<PlayerStatsViewModel> playerStatsViewModelList) {
        GameStats gameStats = new GameStats();

        gameStats.gameID = gameID;
        gameStats.gameStats = new ArrayList<>();

        for (PlayerStatsViewModel playerStatsViewModel : playerStatsViewModelList) {
            GameStats.Stats stats = new GameStats.Stats();
            stats.playerID = playerStatsViewModel.getPlayerID();
            stats.assists = Integer.valueOf(playerStatsViewModel.getAssists());
            stats.goals = Integer.valueOf(playerStatsViewModel.getGoals());
            stats.yellow_cards = Integer.valueOf(playerStatsViewModel.getYellowCards());
            stats.red_cards = Integer.valueOf(playerStatsViewModel.getRedCards());
            stats.present = playerStatsViewModel.getPresence();

            gameStats.gameStats.add(stats);
        }

        return gameStats;
    }

}
