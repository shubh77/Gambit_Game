package com.test.gambit.game.player.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.test.gambit.game.R;
import com.test.gambit.game.player.view.ViewHolder;

/**
 * Created by Shubham on 22-04-2019.
 */

public class PlayersAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<PlayerData> playerDataArrayList;

    public PlayersAdapter(ArrayList<PlayerData> list) {
        playerDataArrayList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.player_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(playerDataArrayList.get(position).getFirstName() != null) {
            holder.first_name.setText(playerDataArrayList.get(position).getFirstName());
        }
        if(playerDataArrayList.get(position).getPosition() != null) {
            holder.position.setText(playerDataArrayList.get(position).getPosition());
        }
        if(playerDataArrayList.get(position).getId() != null) {
            holder.teamId.setText(""+playerDataArrayList.get(position).getId());
        }
        if(playerDataArrayList.get(position).getLastName() != null) {
            holder.last_name.setText(playerDataArrayList.get(position).getLastName());
        }
    }

    @Override
    public int getItemCount() {
        return playerDataArrayList.size();
    }
}
