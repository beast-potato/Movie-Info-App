package com.beastpotato.movieinformation.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.databinding.TestViewpagerLayoutBinding;
import com.beastpotato.movieinformation.viewmodels.TestViewpagerViewModel;

/**
 * Created by Oleksiy on 2/25/2016.
 */
public class TestFragment extends BaseFragment {
    private TestViewpagerLayoutBinding binding;

    public static TestFragment newInstance() {
        TestFragment f = new TestFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_viewpager_layout, container, false);
        binding.setTestViewpagerViewModel(new TestViewpagerViewModel(binding));
        return binding.getRoot();
    }

    @Override
    protected void setupView(View view) {

    }
}
