package com.test.gambit.game.player.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test.gambit.game.R;

/**
 * <h1>ViewHolder</h1>
 * This class will work as the View Holder for Player Page, which will define all the views.
 * @author Shubham
 * @since 22-04-2019
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView first_name, last_name, position, teamId;

    public ViewHolder(View v){
        super(v);
        first_name = v.findViewById(R.id.first_name);
        last_name = v.findViewById(R.id.last_name);
        position = v.findViewById(R.id.position);
        teamId = v.findViewById(R.id.teamId);
    }
}
