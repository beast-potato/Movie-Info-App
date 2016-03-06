package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.Result;

/**
 * Created by Oleksiy on 3/1/2016.
 */
public class TvDetailViewModel extends BaseObservable {
    private Result tvModel;

    public TvDetailViewModel(Result tvCardModel) {
        tvModel = tvCardModel;
    }

    @Bindable
    public String getPoster() {
        return tvModel.posterPath;
    }

    @Bindable
    public String getName() {
        return tvModel.name;
    }

    @Bindable
    public String getDescription() {
        return tvModel.overview;
    }

    @Bindable
    public String getReleaseDate() {
        return tvModel.firstAirDate;
    }

    @Bindable
    public Double getRating() {
        return tvModel.voteAverage == null ? 0 : tvModel.voteAverage / 10.0f;
    }
}
