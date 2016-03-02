package com.beastpotato.movieinformation.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.databinding.MovieDetailLayoutBinding;
import com.beastpotato.movieinformation.databinding.TvDetailLayoutBinding;
import com.beastpotato.movieinformation.viewmodels.MovieCardViewModel;
import com.beastpotato.movieinformation.viewmodels.MovieDetailViewModel;
import com.beastpotato.movieinformation.viewmodels.TvCardViewModel;
import com.beastpotato.movieinformation.viewmodels.TvDetailViewModel;

/**
 * Created by Oleksiy on 3/1/2016.
 */
public class DetailFragment extends BaseFragment {
    private static final String KEY_MOVIE_MODEL = "movie_model";
    private static final String KEY_TV_MODEL = "tv_model";
    private com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result movieCardModel;
    private com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.Result tvCardModel;
    private MovieDetailLayoutBinding movieBinding;
    private TvDetailLayoutBinding tvBinding;

    public static DetailFragment newInstance(MovieCardViewModel movieCardViewModel) {
        DetailFragment fragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(KEY_MOVIE_MODEL, movieCardViewModel.movieModel.get());
        fragment.setArguments(b);
        return fragment;
    }

    public static DetailFragment newInstance(TvCardViewModel tvCardViewModel) {
        DetailFragment fragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(KEY_TV_MODEL, tvCardViewModel.tvModel.get());
        fragment.setArguments(b);
        return fragment;
    }

    private void getArgs() {
        movieCardModel = getArguments().getParcelable(KEY_MOVIE_MODEL);
        tvCardModel = getArguments().getParcelable(KEY_TV_MODEL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getArgs();
        View view = null;
        if (movieCardModel != null) {
            movieBinding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_layout, container, false);
            view = movieBinding.getRoot();
        }
        if (tvCardModel != null) {
            tvBinding = DataBindingUtil.inflate(inflater, R.layout.tv_detail_layout, container, false);
            view = tvBinding.getRoot();
        }
        return view;
    }

    @Override
    protected void setupView(View view) {
        if (movieCardModel != null) {
            movieBinding.setMovieViewModel(new MovieDetailViewModel(movieCardModel));
        }
        if (tvCardModel != null) {
            tvBinding.setTvViewModel(new TvDetailViewModel(tvCardModel));
        }
    }
}
