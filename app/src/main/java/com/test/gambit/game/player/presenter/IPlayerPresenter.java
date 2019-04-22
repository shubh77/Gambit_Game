package com.test.gambit.game.player.presenter;

import android.content.Context;

/**
 * Created by Shubham on 22-04-2019.
 */

public interface IPlayerPresenter {

    void initDataBase(Context context);

    int getMiniMumPageSize();

    void loadMore();

    void getPlayers(int page);
    void getPlayerById(String name);
    void stop();

    boolean isLoading();
}
