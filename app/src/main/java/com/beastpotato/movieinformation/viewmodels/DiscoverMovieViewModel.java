package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;

import com.beastpotato.movieinformation.BR;
import com.beastpotato.movieinformation.MyApplication;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.adapters.ItemBinder;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.DiscoverMovieEndpointApiResponse;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class DiscoverMovieViewModel extends BaseObservable {
    public ObservableField<GridLayoutManager> layoutManager = new ObservableField<>();
    private DiscoverMovieEndpointApiResponse discoverMovieModel;
    private ItemBinder<MovieCardViewModel> itemBinding;

    public DiscoverMovieViewModel() {
        layoutManager.set(new GridLayoutManager(MyApplication.getContext(), 2));
        itemBinding = new ItemBinder<>(R.layout.movie_card_layout, BR.movieCardViewModel);
    }

    @Bindable
    public ItemBinder<MovieCardViewModel> getItemBinding() {
        return itemBinding;
    }

    public void setItemBinding(ItemBinder<MovieCardViewModel> itemBinding) {
        this.itemBinding = itemBinding;
        notifyPropertyChanged(BR.itemBinding);
    }

    @Bindable
    public DiscoverMovieEndpointApiResponse getDiscoverMovieModel() {
        return discoverMovieModel;
    }

    public void setDiscoverMovieModel(DiscoverMovieEndpointApiResponse discoverMovieModel) {
        this.discoverMovieModel = discoverMovieModel;
        notifyPropertyChanged(BR.discoverMovieModel);
        notifyPropertyChanged(BR.movieCardViewModels);
    }

    @Bindable
    public List<MovieCardViewModel> getMovieCardViewModels() {
        List<MovieCardViewModel> movieCardViewModelList = new ArrayList<>();
        if (discoverMovieModel != null) {
            for (Result movie : discoverMovieModel.getResults()) {
                movieCardViewModelList.add(new MovieCardViewModel(movie));
            }
        }
        return movieCardViewModelList;
    }
}
