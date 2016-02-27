package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.activities.MainActivity;
import com.beastpotato.movieinformation.adapters.ItemBinder;
import com.beastpotato.movieinformation.databinding.DiscoverViewpagerLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksiy on 2/25/2016.
 */
public class DiscoverViewpagerViewModel extends BaseObservable {
    private List<ItemBinder> itemBindings = new ArrayList<>();
    private List<String> tabStrings = new ArrayList<>();
    private DiscoverViewpagerLayoutBinding binding;

    public DiscoverViewpagerViewModel(DiscoverViewpagerLayoutBinding binding, MainActivity activity) {
        this.binding = binding;
        itemBindings.add(new ItemBinder(R.layout.discover_movies_layout, BR.discoverMoviesViewModel, new DiscoverMovieViewModel(activity)));
        tabStrings.add(activity.getString(R.string.movies));
        itemBindings.add(new ItemBinder(R.layout.discover_tv_layout, BR.discoverTvViewModel, new DiscoverTvViewModel(activity)));
        tabStrings.add(activity.getString(R.string.tv_shows));
    }

    @Bindable
    public List<ItemBinder> getItemBindings() {
        return itemBindings;
    }

    @Bindable
    public List<String> getTabStrings() {
        return tabStrings;
    }

    @Bindable
    public TabLayout getTabLayout() {
        return binding.tabs;
    }

    @Bindable
    public TabLayout.OnTabSelectedListener getOnTabSelectedListener() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(binding.viewPager.getContext(), "tab selected " + tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }
}
