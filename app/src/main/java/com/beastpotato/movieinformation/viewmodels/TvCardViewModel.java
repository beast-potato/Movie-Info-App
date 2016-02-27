package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.Result;

/**
 * Created by Admin on 2/27/2016.
 */
public class TvCardViewModel extends BaseObservable {
    public ObservableField<Result> tvModel = new ObservableField<>();

    public TvCardViewModel(Result tvModel) {
        this.tvModel.set(tvModel);
    }

    @Bindable
    public String getPosterImagePath() {
        return tvModel.get().posterPath;
    }

    @Bindable
    public String getTitle() {
        return tvModel.get().name;
    }
}
