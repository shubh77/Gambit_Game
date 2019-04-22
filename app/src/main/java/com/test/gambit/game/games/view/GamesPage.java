package com.test.gambit.game.games.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.test.gambit.game.R;
import com.test.gambit.game.common.Util;
import com.test.gambit.game.games.model.Data;
import com.test.gambit.game.games.model.GamesAdapter;
import com.test.gambit.game.games.presenter.GamePresenter;

/**
 * <h1>GamePage</h1>
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesPage#newInstance} factory method to
 * @author Shubham
 * @since 21-04-2019
 */
public class GamesPage extends Fragment implements IGamesPage,SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout data_layout;
    private RelativeLayout loading_layout;
    private TextView status_text;
    private GamesAdapter playersAdapter;
    private ArrayList<Data> datas;
    private GamePresenter presenter;

    public GamesPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment GamesPage.
     */
    public static GamesPage newInstance() {
        GamesPage fragment = new GamesPage();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GamePresenter(this);
        presenter.initDatabase(this.getContext());
        datas = new ArrayList<>();
        playersAdapter = new GamesAdapter(datas);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        presenter.getGameList();

        if (Util.isNetworkAvailable(getContext())) {
           // loading_layout.setVisibility(View.VISIBLE);
            //presenter.getGameList();
        } else {
            //loading_layout.setVisibility(View.GONE);
            Toast.makeText(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private void init(View view) {
        data_layout = view.findViewById(R.id.data_view);
        loading_layout = view.findViewById(R.id.loading_view);
        status_text = view.findViewById(R.id.loading_text);
        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setNestedScrollingEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.games_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(playersAdapter);
    }

    @Override
    public void onRefresh() {
        if (Util.isNetworkAvailable(getContext())) {
            presenter.getGameList();
            swipeRefreshLayout.setRefreshing(false);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateList(List<Data> data) {
        if (data != null) {
            datas.addAll(data);
        } else {
            datas.clear();
        }
        updateDataView();
        playersAdapter.notifyDataSetChanged();
    }

    @Override
    public void loading() {
        data_layout.setVisibility(View.GONE);
        loading_layout.setVisibility(View.VISIBLE);
        status_text.setText("Loading...");
    }

    @Override
    public void error() {
        data_layout.setVisibility(View.GONE);
        loading_layout.setVisibility(View.VISIBLE);
        status_text.setText("Failed...");
    }

    private void updateDataView() {
        if (datas != null && datas.size() > 0) {
            data_layout.setVisibility(View.VISIBLE);
            loading_layout.setVisibility(View.GONE);
        } else {
            data_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
            status_text.setText("Failed...");
        }
    }

    @Override
    public void onDestroy() {
        presenter.stop();
        super.onDestroy();
    }
}
