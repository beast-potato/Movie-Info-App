package com.beastpotato.movieinformation.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.fragments.BaseFragment;
import com.beastpotato.movieinformation.fragments.DiscoverFragment;

public class MainActivity extends AppCompatActivity implements BaseFragment.OnRefreshDoneListener {
    private static int FRAG_TAG = 0;
    private FrameLayout fragmentContainer;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        findViews();
        setListeners();
        showFragment(DiscoverFragment.newInstance());
    }

    private void findViews() {
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

    private void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCurrentFragment().refresh(MainActivity.this);
            }
        });
    }

    private BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(FRAG_TAG - 1));
    }

    public void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showFragment(BaseFragment fragment) {
        showLoading();
        fragment.setRefreshListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragment, "" + FRAG_TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
        FRAG_TAG++;
    }

    @Override
    public void onRefreshDone() { //current fragment refreshed data
        hideLoading();
    }
}
