package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.beastpotato.movieinformation.activities.MainActivity;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result;
import com.beastpotato.movieinformation.fragments.DetailFragment;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class MovieCardViewModel extends BaseObservable {
    public ObservableField<Result> movieModel = new ObservableField<>();
    private MainActivity activity;

    public MovieCardViewModel(Result movieInfo, MainActivity activity) {
        movieModel.set(movieInfo);
        this.activity = activity;
    }

    @Bindable
    public String getPosterImagePath() {
        return movieModel.get().posterPath;
    }

    @Bindable
    public String getTitle() {
        return movieModel.get().title;
    }

    @Bindable
    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showFragment(DetailFragment.newInstance(MovieCardViewModel.this));
            }
        };
    }
}
