package com.beastpotato.movieinformation.viewmodels;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;

import com.android.volley.VolleyError;
import com.beastpotato.movieinformation.BR;
import com.beastpotato.movieinformation.Constants;
import com.beastpotato.movieinformation.MyApplication;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.activities.MainActivity;
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
    private List<ItemBinder> itemBindings;
    private MainActivity mainActivity;


    public DiscoverMovieViewModel(String firstPathPart, String secondPathPart, MainActivity activity) {
        super(DiscoverMovieEndpointApiRequest.class, Constants.baseUrl, activity);
        this.layoutManager.set(new GridLayoutManager(MyApplication.getContext(), 2));
        this.itemBindings = new ArrayList<>();
        this.mainActivity = activity;
        getModel().setApiKey(Constants.apiKey);
        getModel().setContentType(Constants.contentTypeJson);
        getModel().setPage(10);
        getModel().setFirstPathPart(firstPathPart);
        getModel().setSecondPathPart(secondPathPart);
        loadData(new ApiRequest.RequestCompletion<DiscoverMovieEndpointApiResponse>() {
            @Override
            public void onResponse(DiscoverMovieEndpointApiResponse data) {
                setDiscoverMovieModel(data);
                mainActivity.hideLoading();
            }

            @Override
            public void onError(VolleyError error) {
                mainActivity.showOkError(R.string.network_error_title, R.string.network_error_msg, null, null);
            }
        });
    }

    @Override
    public void loadData(final ApiRequest.RequestCompletion<DiscoverMovieEndpointApiResponse> completion) {
        mainActivity.showLoading();
        getModel().send(completion);
    }


    @Bindable
    public DiscoverMovieEndpointApiResponse getDiscoverMovieModel() {
        return discoverMovieModel;
    }

    public void setDiscoverMovieModel(DiscoverMovieEndpointApiResponse discoverMovieModel) {
        this.discoverMovieModel = discoverMovieModel;
        notifyPropertyChanged(BR.discoverMovieModel);
        notifyPropertyChanged(BR.itemBindings);
    }

    @Bindable
    public List<ItemBinder> getItemBindings() {
        itemBindings = new ArrayList<>();
        if (discoverMovieModel != null && discoverMovieModel.results != null) {
            for (Result movie : discoverMovieModel.results) {
                itemBindings.add(new ItemBinder(R.layout.movie_card_layout, BR.movieCardViewModel, new MovieCardViewModel(movie, mainActivity)));
            }
        }
        return itemBindings;
    }
}
