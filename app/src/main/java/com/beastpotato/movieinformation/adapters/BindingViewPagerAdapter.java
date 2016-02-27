package com.beastpotato.movieinformation.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Oleksiy on 2/25/2016.
 */
public class BindingViewPagerAdapter extends PagerAdapter {
    private List<ItemBinder> bindings;

    public BindingViewPagerAdapter(List<ItemBinder> bindings) {
        this.bindings = bindings;
    }

    @Override
    public int getCount() {
        return bindings.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemBinder itemBinding = bindings.get(position);
        ViewDataBinding viewBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), itemBinding.getLayoutItemId(), container, false);
        viewBinding.setVariable(itemBinding.getBoundVariableId(), bindings.get(position).getBoundObject());
        viewBinding.executePendingBindings();
        View itemView = viewBinding.getRoot();
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
