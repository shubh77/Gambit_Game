package com.test.gambit.game.common;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <h1>ApiHandler</h1>
 * This class is used for Handling the API.
 */
public class ApiHandler {

    private static  ApiHandler handler;
    private Retrofit retrofit;
    private ApiHandler()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiHandler getHandler() {
        if (handler == null ) {
            handler = new ApiHandler();
        }
        return handler;
    }

    public Retrofit getClint() {
      return   retrofit;
    }

    public GamesApiStructure getApi() {
        return ApiHandler
                .getHandler()
                .getClint().create(GamesApiStructure.class);
    }
}
