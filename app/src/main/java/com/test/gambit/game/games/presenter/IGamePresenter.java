package com.test.gambit.game.games.presenter;

import android.content.Context;

/**
 * Created by Shubham on 22-04-2019.
 */

public interface IGamePresenter {

    void initDatabase(Context context);
    void getGameList();

    void stop();
}
