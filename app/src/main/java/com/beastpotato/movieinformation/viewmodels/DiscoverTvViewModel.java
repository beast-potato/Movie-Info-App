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
import com.beastpotato.movieinformation.endpoints.DiscoverTvEndpointApiRequest;
import com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.DiscoverTvEndpointApiResponse;
import com.beastpotato.movieinformation.endpoints.discovertvendpointresponse.Result;
import com.beastpotato.potato.api.net.ApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/27/2016.
 */
public class DiscoverTvViewModel extends BaseRequestViewModel<DiscoverTvEndpointApiResponse, DiscoverTvEndpointApiRequest.Fields, DiscoverTvEndpointApiRequest> {
    public ObservableField<GridLayoutManager> layoutManager = new ObservableField<>();
    private DiscoverTvEndpointApiResponse discoverTvModel;
    private List<ItemBinder> itemBindings;
    private MainActivity mainActivity;

    public DiscoverTvViewModel(String firstPathPart, String secondPathPart, MainActivity activity) {
        super(DiscoverTvEndpointApiRequest.class, Constants.baseUrl, activity);
        this.layoutManager.set(new GridLayoutManager(MyApplication.getContext(), 2));
        this.itemBindings = new ArrayList<>();
        this.mainActivity = activity;
        getModel().setApiKey(Constants.apiKey);
        getModel().setContentType(Constants.contentTypeJson);
        getModel().setFirstPathPart(firstPathPart);
        getModel().setSecondPathPart(secondPathPart);
        getModel().setPage(10);
        loadData(new ApiRequest.RequestCompletion<DiscoverTvEndpointApiResponse>() {
            @Override
            public void onResponse(DiscoverTvEndpointApiResponse data) {
                setDiscoverTvModel(data);
                mainActivity.hideLoading();
            }

            @Override
            public void onError(VolleyError error) {
                mainActivity.showOkError(R.string.network_error_title, R.string.network_error_msg, null, null);
            }
        });
    }

    @Override
    public void loadData(ApiRequest.RequestCompletion<DiscoverTvEndpointApiResponse> completion) {
        mainActivity.showLoading();
        getModel().send(completion);
    }

    @Bindable
    public DiscoverTvEndpointApiResponse getDiscoverTvModel() {
        return discoverTvModel;
    }

    public void setDiscoverTvModel(DiscoverTvEndpointApiResponse discoverTvModel) {
        this.discoverTvModel = discoverTvModel;
        notifyPropertyChanged(BR.discoverTvModel);
        notifyPropertyChanged(BR.itemBindings);
    }

    @Bindable
    public List<ItemBinder> getItemBindings() {
        itemBindings = new ArrayList<>();
        if (discoverTvModel != null && discoverTvModel.results != null) {
            for (Result movie : discoverTvModel.results) {
                itemBindings.add(new ItemBinder(R.layout.tv_card_layout, BR.tvCardViewModel, new TvCardViewModel(movie)));
            }
        }
        return itemBindings;
    }
}
