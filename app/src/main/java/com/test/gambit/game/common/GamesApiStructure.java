package com.test.gambit.game.common;

import com.test.gambit.game.games.model.GamesData;
import com.test.gambit.game.player.model.PlayerDataHolder;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <h2>GamesApiStructure</h2>
 * This is an Interface for Retrofit for API.
 */
public interface GamesApiStructure {

    @GET("games")
    Observable<GamesData> getGames();

    @GET("players")
    Observable<PlayerDataHolder> getPlayer(@Query("page") int page, @Query("per_page") int per_page);

    @GET("players")
    Observable<PlayerDataHolder> getPlayerById(@Query("search") String name);
}
