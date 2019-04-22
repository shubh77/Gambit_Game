package com.test.gambit.game.player.view;

import java.util.List;

import com.test.gambit.game.player.model.PlayerData;

/**
 * Created by Shubham on 22-04-2019.
 */

public interface IPlayerPage {

    void updateLoadMore(List<PlayerData> players);

    void loading();

    void updatePayer(List<PlayerData> players);

    void updatePayerById(List<PlayerData> players);
}
