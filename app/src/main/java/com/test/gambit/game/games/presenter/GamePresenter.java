package com.test.gambit.game.games.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.test.gambit.game.common.ApiHandler;
import com.test.gambit.game.games.model.GameModel;
import com.test.gambit.game.games.model.GamesData;
import com.test.gambit.game.games.view.IGamesPage;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <h1>GamePresenter</h1>
 * This class represents the Presentation for Game Page.
 * @author Shubham
 * @since 22-04-2019.
 */

public class GamePresenter implements IGamePresenter {

    private List<Disposable> disposables;
    private IGamesPage gamesPage;
    private GameModel gameModel;
    public GamePresenter(IGamesPage gamesPage) {
        this.gamesPage = gamesPage;
        disposables = new ArrayList<>();
        gameModel = new GameModel();
    }

    @Override
    public void initDatabase(Context context) {
        gameModel.intData(context);
    }

    @Override
    public void getGameList() {
        gamesPage.loading();
        Observable<GamesData> observable = ApiHandler.getHandler().getApi().getGames();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GamesData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(GamesData value) {
                        gamesPage.updateList(value.getData());
                        gameModel.saveUserGameData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        GamesData player = gameModel.getStoredGames();
                        if(player != null && player.getData().size() > 0) {
                            gamesPage.updateList(player.getData());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void stop() {
        for(Disposable disposable: disposables) {
            disposable.dispose();
        }
    }
}
