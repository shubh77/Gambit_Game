package com.test.gambit.game.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.gambit.game.R;
import com.test.gambit.game.common.Pager;
import com.test.gambit.game.Home.View.HomeView;

/**
 * <h1>MainActivity</h1>
 * This class is the Main Activity which will work as the Default class for the Application.
 * @author Shubham
 * @since 21-04-2019
 */
public class MainActivity extends AppCompatActivity  implements HomeView, TabLayout.OnTabSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void initView() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_view);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPager  viewPager = (ViewPager) findViewById(R.id.pager_View);
        Pager adapter = new Pager(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
