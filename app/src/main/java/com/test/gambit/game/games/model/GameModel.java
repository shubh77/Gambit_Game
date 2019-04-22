package com.test.gambit.game.games.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import com.test.gambit.game.data.localdata.SharedPager;

public class GameModel {

    private SharedPager sharedPager;

    public void intData(Context context) {
        sharedPager = new SharedPager(context);
    }

    public void saveUserGameData(GamesData gamesData) {
        String data = new Gson().toJson(gamesData);
        save(data);
        Log.d("23er", "cfds : " + data);
    }
    private void save(String data) {
        sharedPager.saveGameData(data);
    }

    public GamesData getStoredGames() {
        String data = sharedPager.getGameData();
        Log.d("23er1", "cfds : " + data);
        GamesData gamesData = new Gson().fromJson(data,GamesData.class);
        return  gamesData;
    }
}
