package com.beastpotato.movieinformation.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.databinding.DiscoverViewpagerLayoutBinding;
import com.beastpotato.movieinformation.viewmodels.DiscoverViewpagerViewModel;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class ContentFragment extends BaseFragment {
    private static final String PATH_FIRST_MOVIE = "path_first_movie";
    private static final String PATH_SECOND_MOVIE = "path_second_movie";
    private static final String PATH_FIRST_TV = "path_first_tv";
    private static final String PATH_SECOND_TV = "path_second_tv";
    private DiscoverViewpagerLayoutBinding binding;
    private String pathPartFirstMovie, pathPartSecondMovie, pathPartFirstTV, pathPartSecondTV;

    public static ContentFragment newInstance(String firstPathPartMovie, String secondPathPartMovie, String firstPathPartTV, String secondPathPartTV) {
        ContentFragment fragment = new ContentFragment();
        Bundle b = new Bundle();
        b.putString(PATH_FIRST_MOVIE, firstPathPartMovie);
        b.putString(PATH_SECOND_MOVIE, secondPathPartMovie);
        b.putString(PATH_FIRST_TV, firstPathPartTV);
        b.putString(PATH_SECOND_TV, secondPathPartTV);
        fragment.setArguments(b);
        return fragment;
    }

    private void getArgs() {
        pathPartFirstMovie = getArguments().getString(PATH_FIRST_MOVIE);
        pathPartSecondMovie = getArguments().getString(PATH_SECOND_MOVIE);
        pathPartFirstTV = getArguments().getString(PATH_FIRST_TV);
        pathPartSecondTV = getArguments().getString(PATH_SECOND_TV);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getArgs();
        binding = DataBindingUtil.inflate(inflater, R.layout.discover_viewpager_layout, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupView(View view) {
        binding.setDiscoverViewpagerViewModel(new DiscoverViewpagerViewModel(pathPartFirstMovie, pathPartSecondMovie, pathPartFirstTV, pathPartSecondTV, binding, getBaseActivity()));
    }

}
