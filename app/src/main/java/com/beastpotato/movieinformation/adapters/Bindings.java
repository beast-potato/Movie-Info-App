package com.beastpotato.movieinformation.adapters;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.beastpotato.movieinformation.managers.ImageManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Oleksiy on 1/31/2016.
 */
public class Bindings {

    @BindingAdapter(value = {"tabStrings"})
    public static void addTabText(TabLayout tabLayout, List<String> tabStrings) {
        for (String tabString : tabStrings) {
            tabLayout.addTab(tabLayout.newTab().setText(tabString));
        }
    }

    @BindingAdapter(value = {"itemBindings"})
    public static void showPagerList(ViewPager viewPager, List<ItemBinder> itemBindings) {
        viewPager.setAdapter(new BindingViewPagerAdapter(itemBindings));
    }

    @BindingAdapter(value = {"tabLayout", "tabStrings", "itemBindings"})
    public static void showPagerListWithTabs(final ViewPager viewPager, final TabLayout tabLayout, List<String> tabStrings, List<ItemBinder> itemBindings) {
        if (itemBindings == null) {
            return;
        }
        viewPager.setAdapter(new BindingViewPagerAdapter(itemBindings));
        for (String tabString : tabStrings) {
            tabLayout.addTab(tabLayout.newTab().setText(tabString));
        }
        if (tabLayout != null) {
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            viewPager.clearOnPageChangeListeners();
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tabLayout.getTabAt(position).select();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @BindingAdapter(value = {"itemBindings", "layoutManager"})
    public static void showItemsList(RecyclerView recyclerView, List<ItemBinder> itemBindings, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BindingRecyclerViewAdapter(itemBindings));
    }

    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, Drawable drawable) {
        Picasso.with(imageView.getContext()).load(imageUrl).placeholder(drawable).into(imageView);
    }

    @BindingAdapter(value = {"posterImageUrl", "placeholder"}, requireAll = false)
    public static void loadPosterImage(ImageView imageView, String imageUrl, Drawable drawable) {
        Picasso.with(imageView.getContext()).load(ImageManager.getInstance().getPosterImageUrl(imageUrl)).placeholder(drawable).into(imageView);
    }

}
