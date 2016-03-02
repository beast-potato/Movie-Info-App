package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.beastpotato.movieinformation.activities.MainActivity;
import com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.Result;
import com.beastpotato.movieinformation.fragments.DetailFragment;

/**
 * Created by Admin on 2/27/2016.
 */
public class TvCardViewModel extends BaseObservable {
    public ObservableField<Result> tvModel = new ObservableField<>();
    private MainActivity activity;

    public TvCardViewModel(Result tvModel, MainActivity activity) {
        this.tvModel.set(tvModel);
        this.activity = activity;
    }

    @Bindable
    public String getPosterImagePath() {
        return tvModel.get().posterPath;
    }

    @Bindable
    public String getTitle() {
        return tvModel.get().name;
    }

    @Bindable
    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showFragment(DetailFragment.newInstance(TvCardViewModel.this));
            }
        };
    }
}
