package com.test.gambit.game.games.view;

import java.util.List;

import com.test.gambit.game.games.model.Data;

/**
 * Created by Shubham on 22-04-2019.
 */

public interface IGamesPage {

    void updateList(List<Data> data);

    void loading();

    void error();
}
