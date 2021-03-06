package com.goalieunionapps.grmacsfc.activities.admin.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.activities.admin.viewmodels.PlayerStatsViewModel;

import java.util.ArrayList;

/**
 * Created on 3/27/17.
 */

public class AdminEditsStatsAdapter extends RecyclerView.Adapter<AdminEditsStatsAdapter.StatsViewHolder> {

    private ArrayList<PlayerStatsViewModel> stats;

    public AdminEditsStatsAdapter(ArrayList<PlayerStatsViewModel> stats) {

        this.stats = stats;
    }

    public ArrayList<PlayerStatsViewModel> getStats() {
        return stats;
    }

    public boolean statsChanged() {
        for (PlayerStatsViewModel playerStatsViewModel : stats) {
            if (playerStatsViewModel.isDirty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public StatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_admin_stats_player_card, parent, false);

        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatsViewHolder holder, int position) {
        final PlayerStatsViewModel model = stats.get(position);

        holder.getBinding().setVariable(BR.data, model);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    public static class StatsViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public StatsViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
