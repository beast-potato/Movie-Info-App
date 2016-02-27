package com.beastpotato.movieinformation.activities;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.fragments.BaseFragment;
import com.beastpotato.movieinformation.fragments.DiscoverFragment;

public class MainActivity extends AppCompatActivity {
    private static int FRAG_TAG = 0;
    private FrameLayout fragmentContainer;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private View loadingOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        findViews();
        setListeners();
        setData();
    }

    private void findViews() {
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        loadingOverlay = findViewById(R.id.main_loader);
    }

    private void setListeners() {

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                new Toolbar(MainActivity.this),
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                drawerToggle.syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                drawerToggle.syncState();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.isChecked()) {
                    return false;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.discover_navigation_item:
                        showFragment(DiscoverFragment.newInstance());
                        return true;
                    case R.id.todo_navigation_item:
                        //todo
                        return true;
                }
                return false;
            }
        });
    }

    private void setData() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        showFragment(DiscoverFragment.newInstance());
    }

    private BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(FRAG_TAG - 1));
    }

    public void showLoading() {
        loadingOverlay.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loadingOverlay.setVisibility(View.GONE);
    }

    public void showFragment(BaseFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragment, "" + FRAG_TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
        FRAG_TAG++;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_search:
                Toast.makeText(this, "todo: show search", Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void showOkError(String title, String msg, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancelClickListener) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(getString(R.string.ok), onOkClickListener);
        builder.setNegativeButton(getString(R.string.cancel), onCancelClickListener);
        builder.show();
    }

    public void showOkError(@StringRes int titleRes, @StringRes int msgRes, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancelClickListener) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getString(titleRes));
        builder.setMessage(getString(msgRes));
        builder.setPositiveButton(getString(R.string.ok), onOkClickListener);
        builder.setNegativeButton(getString(R.string.cancel), onCancelClickListener);
        builder.show();
    }
}
