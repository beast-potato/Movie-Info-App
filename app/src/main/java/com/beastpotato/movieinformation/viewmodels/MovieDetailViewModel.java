package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result;

/**
 * Created by Oleksiy on 3/1/2016.
 */
public class MovieDetailViewModel extends BaseObservable {
    private Result movieModel;

    public MovieDetailViewModel(Result movieCardModel) {
        movieModel = movieCardModel;
    }

    @Bindable
    public String getPoster() {
        return movieModel.posterPath;
    }

    @Bindable
    public String getName() {
        return movieModel.title;
    }

    @Bindable
    public String getDescription() {
        return movieModel.overview;
    }

    @Bindable
    public String getReleaseDate() {
        return movieModel.releaseDate;
    }

    @Bindable
    public Double getRating() {
        return movieModel.voteAverage == null ? 0 : movieModel.voteAverage / 10.0f;
    }
}
