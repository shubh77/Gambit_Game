package com.test.gambit.game.games.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.test.gambit.game.R;

/**
 * Created by Shubham on 22-04-2019.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private ArrayList<Data> gamesDataArrayList;

    public GamesAdapter(ArrayList<Data> list) {
        gamesDataArrayList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_homeAbb, tv_visitorAbb, tv_homeFullName, tv_visitorFullName;

        public ViewHolder(View v){
            super(v);
            tv_homeAbb = v.findViewById(R.id.tv_homeAbb);
            tv_visitorAbb = v.findViewById(R.id.tv_visitorAbb);
            tv_homeFullName = v.findViewById(R.id.tv_homeFullName);
            tv_visitorFullName = v.findViewById(R.id.tv_visitorFullName);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.game_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(gamesDataArrayList.get(position).getHomeTeam().getAbbreviation() != null) {
            holder.tv_homeAbb.setText(gamesDataArrayList.get(position).getHomeTeam().getAbbreviation());
        }
        if(gamesDataArrayList.get(position).getHomeTeam().getFullName() != null) {
            holder.tv_homeFullName.setText(gamesDataArrayList.get(position).getHomeTeam().getFullName());
        }
        if(gamesDataArrayList.get(position).getVisitorTeam().getAbbreviation() != null) {
            holder.tv_visitorAbb.setText(""+gamesDataArrayList.get(position).getVisitorTeam().getAbbreviation());
        }
        if(gamesDataArrayList.get(position).getVisitorTeam().getFullName() != null) {
            holder.tv_visitorFullName.setText(gamesDataArrayList.get(position).getVisitorTeam().getFullName());
        }
    }

    @Override
    public int getItemCount() {
        return gamesDataArrayList.size();
    }
}
