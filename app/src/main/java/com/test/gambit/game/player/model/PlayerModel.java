package com.test.gambit.game.player.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import com.test.gambit.game.data.localdata.SharedPager;

public class PlayerModel {

    private SharedPager sharedPager;

    public void intData(Context context) {
        sharedPager = new SharedPager(context);
    }

    public void saveUserData(PlayerDataHolder playerDataHolder) {
        String data = new Gson().toJson(playerDataHolder);
        save(data);
        Log.d("23er", "cfds : " + data);

    }
    private void save(String data) {
        sharedPager.savePlayerData(data);
    }

    public PlayerDataHolder getStoredPlayer() {
        String data = sharedPager.getPlayerData();
        Log.d("23er1", "cfds : " + data);
        PlayerDataHolder playerDataHolder = new Gson().fromJson(data,PlayerDataHolder.class);
        return  playerDataHolder;
    }
}
