package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.adapters.ItemBinder;
import com.beastpotato.movieinformation.databinding.TestViewpagerLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksiy on 2/25/2016.
 */
public class TestViewpagerViewModel extends BaseObservable {
    private List<ItemBinder<TestViewModel>> itemBindings;
    private List<TestViewModel> items;
    private List<String> tabStrings;
    private TestViewpagerLayoutBinding binding;

    public TestViewpagerViewModel(TestViewpagerLayoutBinding binding) {
        this.binding = binding;
        items = new ArrayList<>();
        itemBindings = new ArrayList<>();
        tabStrings = new ArrayList<>();
        items.add(new TestViewModel("one"));
        itemBindings.add(new ItemBinder<TestViewModel>(R.layout.test_layout, BR.testModel));
        tabStrings.add("tab one");
        items.add(new TestViewModel("two"));
        itemBindings.add(new ItemBinder<TestViewModel>(R.layout.test_layout, BR.testModel));
        tabStrings.add("tab two");
    }

    @Bindable
    public List<ItemBinder<TestViewModel>> getItemBindings() {
        return itemBindings;
    }

    @Bindable
    public List<TestViewModel> getItems() {
        return items;
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
