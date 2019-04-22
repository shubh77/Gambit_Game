package com.test.gambit.game.player.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.test.gambit.game.common.ApiHandler;
import com.test.gambit.game.player.model.PlayerDataHolder;
import com.test.gambit.game.player.model.PlayerModel;
import com.test.gambit.game.player.view.IPlayerPage;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <h1>PlayerPresenter</h1>
 * This class represents the Presentation for Player Page.
 * @author Shubham
 * @since 22-04-2019.
 */

public class PlayerPresenter implements IPlayerPresenter {

    private IPlayerPage playerInterface;
    private List<Disposable> disposables;
    private PlayerModel playerModel;
    private boolean isLoading = false;
    private static final int Page_SIZE = 10;
    private static final int MIN_PAGE_SIZE = 5;
    private int CURRENT_PAGE= 0;

    public PlayerPresenter(IPlayerPage playerInterface) {
        this.playerInterface = playerInterface;
        disposables = new ArrayList<>();
        playerModel = new PlayerModel();
    }

    @Override
    public void initDataBase(Context context) {
        playerModel.intData(context);
    }

    @Override
    public int getMiniMumPageSize() {
        return MIN_PAGE_SIZE;
    }

    @Override
    public void loadMore() {
        CURRENT_PAGE = CURRENT_PAGE + 1;
        isLoading = true;
        Observable<PlayerDataHolder> observable = ApiHandler.getHandler().getApi().getPlayer(CURRENT_PAGE, Page_SIZE );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayerDataHolder>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(PlayerDataHolder value) {
                        playerInterface.updateLoadMore(value.getData());
                        playerModel.saveUserData(value);
                        isLoading = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoading = false;
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getPlayers(int page) {
        CURRENT_PAGE = page;
        playerInterface.loading();
        isLoading = true;
        Observable<PlayerDataHolder> observable = ApiHandler.getHandler().getApi().getPlayer(page, Page_SIZE );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayerDataHolder>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onNext(PlayerDataHolder value) {
                playerInterface.updatePayer(value.getData());
                playerModel.saveUserData(value);
                isLoading = false;
            }

            @Override
            public void onError(Throwable e) {
                PlayerDataHolder player = playerModel.getStoredPlayer();
                if(player != null && player.getData().size() > 0) {
                    playerInterface.updatePayer(player.getData());
                }
                isLoading = false;
            }

            @Override
            public void onComplete() {

            }
        });
    }
    @Override
    public void getPlayerById(String name) {
        Observable<PlayerDataHolder> observable = ApiHandler.getHandler().getApi().getPlayerById(name);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayerDataHolder>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(PlayerDataHolder value) {
                        playerInterface.updatePayerById(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        playerInterface.updateLoadMore(null);
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

    @Override
    public boolean isLoading() {
        return isLoading;
    }
}
