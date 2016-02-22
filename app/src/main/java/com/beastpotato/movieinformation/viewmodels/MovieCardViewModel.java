package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class MovieCardViewModel extends BaseObservable {
    public ObservableField<Result> movieModel = new ObservableField<>();

    public MovieCardViewModel(Result movieInfo) {
        movieModel.set(movieInfo);
    }

    @Bindable
    public String getPosterImagePath() {
        return movieModel.get().getPosterPath();
    }

    @Bindable
    public String getTitle() {
        return movieModel.get().getTitle();
    }
}
