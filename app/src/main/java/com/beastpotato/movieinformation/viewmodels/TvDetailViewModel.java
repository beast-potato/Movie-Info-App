package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;

import com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.Result;

/**
 * Created by Oleksiy on 3/1/2016.
 */
public class TvDetailViewModel extends BaseObservable {
    private Result tvModel;

    public TvDetailViewModel(Result tvCardModel) {
        tvModel = tvCardModel;
    }
}
