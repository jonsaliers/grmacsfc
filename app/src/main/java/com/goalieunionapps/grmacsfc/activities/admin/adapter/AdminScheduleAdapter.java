package com.goalieunionapps.grmacsfc.activities.admin.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goalieunionapps.grmacsfc.BR;
import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.activities.admin.listeners.AdminClickListener;
import com.goalieunionapps.grmacsfc.activities.admin.viewmodels.GameListItem;
import com.goalieunionapps.grmacsfc.activities.admin.viewmodels.ListItem;
import com.goalieunionapps.grmacsfc.models.Game;
import com.goalieunionapps.grmacsfc.models.SeasonSchedule;

import java.util.ArrayList;

/**
 * Created on 3/27/17.
 */

public class AdminScheduleAdapter extends RecyclerView.Adapter<AdminScheduleAdapter.ScheduleViewHolder> {

    ArrayList<ListItem> items;
    private AdminClickListener listener;

    public AdminScheduleAdapter(SeasonSchedule schedule, AdminClickListener listener) {

        this.listener = listener;

        items = new ArrayList<>();
        for (Game game : schedule.getAllGames()) {
            items.add(new GameListItem(game));
        }
    }

    @Override
    public AdminScheduleAdapter.ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);

        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {

        final ListItem item = items.get(position);

        if (item.getItemType() == ListItem.ItemType.GAME) {
            final GameListItem gameItem = (GameListItem) item;
            final ViewDataBinding binding = holder.getBinding();
            binding.setVariable(BR.game, gameItem);
            binding.setVariable(BR.listener, listener);
            binding.executePendingBindings();
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getLayoutID();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ScheduleViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
