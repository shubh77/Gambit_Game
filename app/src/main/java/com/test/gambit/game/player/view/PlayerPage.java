package com.test.gambit.game.player.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.test.gambit.game.R;
import com.test.gambit.game.common.Util;
import com.test.gambit.game.player.model.PlayerData;
import com.test.gambit.game.player.model.PlayersAdapter;
import com.test.gambit.game.player.presenter.PlayerPresenter;

/**
 * <h1>PlayerPage</h1>
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerPage#newInstance} factory method to
 * @author Shubham
 * @since 21-04-2019
 */
public class PlayerPage extends Fragment implements IPlayerPage, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<PlayerData> datas;
    private PlayersAdapter playersAdapter;
    private PlayerPresenter playerPresenter;
    private LinearLayout data_layout;
    private RelativeLayout loading_layout;
    private TextView status_text;
    private SearchView mSearchView;
    private ImageView closeButton;


    public PlayerPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GamesPage.
     */
    public static PlayerPage newInstance() {
        PlayerPage fragment = new PlayerPage();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new ArrayList<>();
        playerPresenter = new PlayerPresenter(this);
        playerPresenter.initDataBase(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payer_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        playerPresenter.getPlayers(0);
        if (Util.isNetworkAvailable(getContext())) {
        } else {
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
        RecyclerView recyclerView = view.findViewById(R.id.player_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        playersAdapter = new PlayersAdapter(datas);
        recyclerView.setAdapter(playersAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager =
                        (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!playerPresenter.isLoading()) {
                    int currentSize = datas.size() - 2;
                    if (linearLayoutManager != null &&
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() == currentSize &&
                                            currentSize > playerPresenter.getMiniMumPageSize()) {
                        swipeRefreshLayout.setRefreshing(true);
                        playerPresenter.loadMore();
                    }
                }
            }
        });
        mSearchView = view.findViewById(R.id.searchView);
        mSearchView.setQueryHint(getString(R.string.search));
        mSearchView.setIconified(false);

        mSearchView.setOnQueryTextListener(this);
        mSearchView.clearFocus();

        // Get the search close button image view
        closeButton = (ImageView)mSearchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = v.findViewById(R.id.search_src_text);
                //Clear the text from EditText view
                mSearchView.setQueryHint(getString(R.string.search));
                //Clear query
                mSearchView.setQuery("", false);
                //Collapse the action view
                mSearchView.onActionViewExpanded();
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        playerPresenter.getPlayers(0);
        if (!Util.isNetworkAvailable(getContext())) {
            Toast.makeText(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateLoadMore(List<PlayerData> players) {
        swipeRefreshLayout.setRefreshing(false);
        if (players != null) {
            datas.addAll(players);
        }
        playersAdapter.notifyDataSetChanged();
    }

    @Override
    public void loading() {
        data_layout.setVisibility(View.GONE);
        loading_layout.setVisibility(View.VISIBLE);
        status_text.setText("Loading...");
    }


    @Override
    public void updatePayer(List<PlayerData> players) {
        if (players != null) {
            datas.clear();
            datas.addAll(players);
        } else {
            datas.clear();
        }
        updateDataView();
        playersAdapter.notifyDataSetChanged();
    }

    @Override
    public void updatePayerById(List<PlayerData> players) {
        if (players != null) {
            datas.clear();
            datas.addAll(players);
        } else {
            datas.clear();
        }
        playersAdapter.notifyDataSetChanged();
    }

    private void updateDataView() {
        if (datas != null && datas.size() > 0) {
            data_layout.setVisibility(View.VISIBLE);
            loading_layout.setVisibility(View.GONE);
            playersAdapter.notifyDataSetChanged();
        } else {
            data_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
            status_text.setText("Failed...");
        }
    }

    @Override
    public void onDestroy() {
        playerPresenter.stop();
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (Util.isNetworkAvailable(getContext())) {
            playerPresenter.getPlayerById(query);
        } else {
            Toast.makeText(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (Util.isNetworkAvailable(getContext())) {
            playerPresenter.getPlayerById(newText);
        } else {
            Toast.makeText(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
