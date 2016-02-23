package com.beastpotato.movieinformation.viewmodels;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;

import com.android.volley.VolleyError;
import com.beastpotato.movieinformation.BR;
import com.beastpotato.movieinformation.Constants;
import com.beastpotato.movieinformation.MyApplication;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.adapters.ItemBinder;
import com.beastpotato.movieinformation.endpoints.DiscoverMovieEndpointApiRequest;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.DiscoverMovieEndpointApiResponse;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.Result;
import com.beastpotato.potato.api.net.ApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class DiscoverMovieViewModel extends BaseRequestViewModel<DiscoverMovieEndpointApiResponse, DiscoverMovieEndpointApiRequest.Fields, DiscoverMovieEndpointApiRequest> {
    public ObservableField<GridLayoutManager> layoutManager = new ObservableField<>();
    private DiscoverMovieEndpointApiResponse discoverMovieModel;
    private ItemBinder<MovieCardViewModel> itemBinding;


    public DiscoverMovieViewModel(String baseUrl, Context context) {
        super(DiscoverMovieEndpointApiRequest.class, baseUrl, context);
        layoutManager.set(new GridLayoutManager(MyApplication.getContext(), 2));
        itemBinding = new ItemBinder<>(R.layout.movie_card_layout, BR.movieCardViewModel);
        getModel().setApiKey(Constants.apiKey);
        getModel().setContentType(Constants.contentTypeJson);
        getModel().setDiscoverType("movie");
        getModel().setPage(10);
    }

    @Override
    public void loadData(final ApiRequest.RequestCompletion<DiscoverMovieEndpointApiResponse> completion) {
        getModel().send(new ApiRequest.RequestCompletion<DiscoverMovieEndpointApiResponse>() {
            @Override
            public void onResponse(DiscoverMovieEndpointApiResponse data) {
                setDiscoverMovieModel(data);
                completion.onResponse(data);
            }

            @Override
            public void onError(VolleyError error) {
                completion.onError(error);
            }
        });
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
            for (Result movie : discoverMovieModel.results) {
                movieCardViewModelList.add(new MovieCardViewModel(movie));
            }
        }
        return movieCardViewModelList;
    }
}
