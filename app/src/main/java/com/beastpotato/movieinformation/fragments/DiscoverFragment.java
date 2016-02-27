package com.beastpotato.movieinformation.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.databinding.DiscoverViewpagerLayoutBinding;
import com.beastpotato.movieinformation.viewmodels.DiscoverViewpagerViewModel;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class DiscoverFragment extends BaseFragment {
    private DiscoverViewpagerLayoutBinding binding;
    private DiscoverViewpagerViewModel viewModel;
    private Toolbar toolbar;

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        //set args
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.discover_viewpager_layout, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupView(View view) {
        binding.setDiscoverViewpagerViewModel(new DiscoverViewpagerViewModel(binding, getBaseActivity()));
    }

}
