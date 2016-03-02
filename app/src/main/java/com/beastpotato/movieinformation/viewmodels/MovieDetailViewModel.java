package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;

import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result;

/**
 * Created by Oleksiy on 3/1/2016.
 */
public class MovieDetailViewModel extends BaseObservable {
    private Result movieModel;

    public MovieDetailViewModel(Result movieCardModel) {
        movieModel = movieCardModel;
    }
}
