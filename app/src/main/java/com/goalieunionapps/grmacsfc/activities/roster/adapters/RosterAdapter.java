package com.goalieunionapps.grmacsfc.activities.roster.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.goalieunionapps.grmacsfc.R;
import com.goalieunionapps.grmacsfc.Utils.RosterUtils;
import com.goalieunionapps.grmacsfc.models.Player;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by willmetz
 * Edited by jonsaliers on 3/27/17.
 */

public class RosterAdapter extends RecyclerView.Adapter<RosterAdapter.PlayerLineView>
        implements StickyHeaderAdapter<RosterAdapter.HeaderView> {

    private ArrayList<RosterListItem> rosterListItems;
    private final Context context;

    public RosterAdapter(final Context context, List<Player> roster, RecyclerView recyclerView) {

        rosterListItems = new ArrayList<>();

        ArrayList<Player> sortedRoster = RosterUtils.sortRoster(roster);

        for (Player player : sortedRoster) {
            rosterListItems.add(new RosterListItem(player));
        }

        this.context = context;
    }


    @Override
    public RosterAdapter.PlayerLineView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.view_roster_row, parent, false);
        return new PlayerLineView(view);

    }

    @Override
    public void onBindViewHolder(RosterAdapter.PlayerLineView holder, int position) {

        if (getItemViewType(position) == RosterListItem.ROSTER_TYPE) {

            PlayerLineView playerLineView = (PlayerLineView) holder;

            final Resources resources = context.getResources();
            final Player player = rosterListItems.get(position).player;

            playerLineView.setBackgroundColor(getBackgroundColor(player, position));

            playerLineView.setPlayer(player);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return RosterListItem.ROSTER_TYPE;
    }

    @Override
    public int getItemCount() {
        return rosterListItems.size();
    }


    @Override
    public RosterAdapter.HeaderView onCreateHeaderViewHolder(RecyclerView parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_header_roster, parent, false);
        return new HeaderView(view);
    }

    @Override
    public void onBindHeaderViewHolder(RosterAdapter.HeaderView viewHolder) {
        //nothing to do here as the data doesn't change based on position
    }

    private int getBackgroundColor(Player player, int position) {
        if (player.injuredReserved) {
            return ContextCompat.getColor(context, R.color.lightRed);
        } else if (position % 2 == 0) {
            return ContextCompat.getColor(context, R.color.lightGray);
        } else {
            return ContextCompat.getColor(context, android.R.color.white);
        }
    }

    public static class PlayerLineView extends RecyclerView.ViewHolder {

        private Player player;
        private TextView name;
        private TextView number;
        private TextView position;

        public PlayerLineView(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.player_name);
            number = (TextView) itemView.findViewById(R.id.player_number);
            position = (TextView) itemView.findViewById(R.id.player_position);
        }

        public void setPlayer(Player player) {
            this.player = player;

            number.setText(RosterUtils.getNumber(player));
            name.setText(RosterUtils.getFullName(player));
            position.setText(RosterUtils.getPosition(player));
        }

        public void setBackgroundColor(int color) {
            itemView.setBackgroundColor(color);
        }
    }

    public static class HeaderView extends RecyclerView.ViewHolder {
        public HeaderView(View itemView) {
            super(itemView);
        }

        public View getContentView() {
            return itemView;
        }

    }
}
