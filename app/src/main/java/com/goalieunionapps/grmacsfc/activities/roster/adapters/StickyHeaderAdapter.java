package com.goalieunionapps.grmacsfc.activities.roster.adapters;

import android.support.v7.widget.RecyclerView;

/**
 * Created by jonsaliers on 3/27/17.
 */

public interface StickyHeaderAdapter<T extends RecyclerView.ViewHolder> {

    T onCreateHeaderViewHolder(RecyclerView parent);

    void onBindHeaderViewHolder(T viewHolder);
}
