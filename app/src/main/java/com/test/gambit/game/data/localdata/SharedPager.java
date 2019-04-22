package com.test.gambit.game.data.localdata;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <h1>SharedPager</h1>
 * This class is used for storing the Data into Shared Preference.
 * @author Shubham
 * @since 22-04-2019
 */
public class SharedPager {
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PLAYER_KEY = "playerKey";
    private static final String GAME_KEY = "gameKey";
    public SharedPager(Context context) {
        sharedPreferences = context.getSharedPreferences("DataPage", 0);
        editor = sharedPreferences.edit();
    }

    public void savePlayerData(String data) {
        editor.putString(PLAYER_KEY, data);
        editor.apply();
    }

    public String getPlayerData() {
       return sharedPreferences.getString(PLAYER_KEY, null);
    }

    public void saveGameData(String data) {
        editor.putString(GAME_KEY, data);
        editor.apply();
    }

    public String getGameData() {
        return sharedPreferences.getString(GAME_KEY, null);
    }
}
