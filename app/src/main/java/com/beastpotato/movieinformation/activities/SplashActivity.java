package com.beastpotato.movieinformation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.beastpotato.movieinformation.Constants;
import com.beastpotato.movieinformation.R;
import com.beastpotato.movieinformation.endpoints.ConfigEndpointApiRequest;
import com.beastpotato.movieinformation.endpoints.configendpointresponse.ConfigEndpointApiResponse;
import com.beastpotato.movieinformation.managers.ImageManager;
import com.beastpotato.potato.api.net.ApiRequest;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        ConfigEndpointApiRequest configRequest = new ConfigEndpointApiRequest(Constants.baseUrl, this);
        configRequest.setContentType(Constants.contentTypeJson);
        configRequest.setApiKey(Constants.apiKey);
        configRequest.send(new ApiRequest.RequestCompletion<ConfigEndpointApiResponse>() {
            @Override
            public void onResponse(ConfigEndpointApiResponse data) {
                ImageManager.getInstance().setup(data);
                Intent startDiscoverActivity = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(startDiscoverActivity);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}
