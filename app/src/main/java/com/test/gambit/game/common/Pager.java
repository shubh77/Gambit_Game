package com.test.gambit.game.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.gambit.game.games.view.GamesPage;
import com.test.gambit.game.player.view.PlayerPage;

/**
 * <h1>Pager</h1>
 * This class is used for Adapters for ViewPager.
 * @author Shubham
 * @since 21-04-2019.
 */

public class Pager extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Player", "Games"};

    public Pager(FragmentManager fm) {
        super(fm);
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlayerPage.newInstance();
            case 1:
                return GamesPage.newInstance();
            default:
                return PlayerPage.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}

