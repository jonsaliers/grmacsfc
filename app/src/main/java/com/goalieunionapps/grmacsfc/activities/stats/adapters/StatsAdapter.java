package com.goalieunionapps.grmacsfc.activities.stats.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.StatsUtils;
import com.goalieunionapps.grmacsfc.models.PlayerStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Recyclerview adapter for player stats
 */
public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.PlayerStatsViewHolder> {

    private ArrayList<PlayerStats> playerStats;

    public StatsAdapter(List<PlayerStats> playerStats) {
        if (playerStats != null) {
            this.playerStats = new ArrayList<>(playerStats);

            Collections.sort(this.playerStats);
        }
    }

    @Override
    public PlayerStatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.view_stats_player_card, parent, false);

        return new PlayerStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerStatsViewHolder holder, int position) {
        holder.setStats(playerStats.get(position));
    }

    @Override
    public int getItemCount() {
        return playerStats != null ? playerStats.size() : 0;
    }

    protected static class PlayerStatsViewHolder extends RecyclerView.ViewHolder {

        private PlayerStats stats;
        private TextView playerName, goals, yellow_cards, red_cards, gamesPlayed;
        private TextView assists;

        public PlayerStatsViewHolder(View itemView) {
            super(itemView);

            playerName = (TextView) itemView.findViewById(R.id.player_name);
            goals = ButterKnife.findById(itemView, R.id.goals);
            assists = ButterKnife.findById(itemView, R.id.assists);
            yellow_cards = ButterKnife.findById(itemView, R.id.yellow_cards);
            red_cards = ButterKnife.findById(itemView, R.id.red_cards);
            gamesPlayed = ButterKnife.findById(itemView, R.id.games_played);
        }

        public void setStats(PlayerStats playerStats) {
            stats = playerStats;

            playerName.setText(StatsUtils.fullPlayerName(playerStats));
            goals.setText(String.valueOf(stats.goals));
            assists.setText(String.valueOf(stats.assists));
            yellow_cards.setText(String.valueOf(stats.yellow_cards));
            red_cards.setText(String.valueOf(stats.red_cards));
            gamesPlayed.setText(String.valueOf(stats.gamesPlayed));
        }
    }

}
