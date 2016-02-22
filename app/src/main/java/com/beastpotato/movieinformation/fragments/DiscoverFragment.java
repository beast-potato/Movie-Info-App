package com.beastpotato.movieinformation.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.beastpotato.movieinformation.Constants;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.databinding.DiscoverMoviesLayoutBinding;
import com.beastpotato.movieinformation.endpoints.DiscoverMovieEndpointApiRequest;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.DiscoverMovieEndpointApiResponse;
import com.beastpotato.movieinformation.viewmodels.DiscoverMovieViewModel;
import com.beastpotato.potato.api.net.ApiRequest;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class DiscoverFragment extends BaseFragment {
    private DiscoverMoviesLayoutBinding binding;
    private DiscoverMovieViewModel viewModel;

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        //set args
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.discover_movies_layout, container, false);
        viewModel = new DiscoverMovieViewModel();
        binding.setDiscoverMoviesViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    protected void setupView(View view) {
        DiscoverMovieEndpointApiRequest discoverRequest = new DiscoverMovieEndpointApiRequest(Constants.baseUrl, getBaseActivity());
        discoverRequest.setApiKey(Constants.apiKey);
        discoverRequest.setContentType(Constants.contentTypeJson);
        discoverRequest.setDiscoverType("movie");
        discoverRequest.setPage(10);

        discoverRequest.send(new ApiRequest.RequestCompletion<DiscoverMovieEndpointApiResponse>() {
            @Override
            public void onResponse(DiscoverMovieEndpointApiResponse data) {
                viewModel.setDiscoverMovieModel(data);
                setupViewComplete();
            }

            @Override
            public void onError(VolleyError error) {
                error.printStackTrace();
                //todo: create error screen
                Toast.makeText(getBaseActivity(), "request failed!", Toast.LENGTH_LONG).show();
                setupViewComplete();
            }
        });
    }
}
