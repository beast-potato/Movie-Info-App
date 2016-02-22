package com.beastpotato.movieinformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.beastpotato.movieinformation.endpoints.DiscoverMovieEndpointApiRequest;
import com.beastpotato.movieinformation.endpoints.discovermovieendpointresponse.DiscoverMovieEndpointApiResponse;
import com.beastpotato.potato.api.net.ApiRequest;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textbox);

        DiscoverMovieEndpointApiRequest discoverRequest = new DiscoverMovieEndpointApiRequest("http://api.themoviedb.org/3", getApplicationContext());
        discoverRequest.setApiKey("2e2ddf0d141ab64938cf49b95e458392");
        discoverRequest.setContentType("application/json");
        discoverRequest.setDiscoverType("movie");
        discoverRequest.setPage(10);

        List<DiscoverMovieEndpointApiRequest.Fields> invalidFields = discoverRequest.validateFields();
        for (DiscoverMovieEndpointApiRequest.Fields field : invalidFields) {
            Toast.makeText(getApplicationContext(), "Field " + field.name() + " failed validation!", Toast.LENGTH_LONG).show();
            switch (field) {
                case apiKey:
                    //handle case where apiKey is null
                    break;
                case page:
                    //handle case where page number is less than 1 or greater than 100
                    break;
            }
        }
        if (invalidFields.size() == 0) {
            discoverRequest.send(new ApiRequest.RequestCompletion<DiscoverMovieEndpointApiResponse>() {
                @Override
                public void onResponse(DiscoverMovieEndpointApiResponse data) {
                    textView.setText("First movie title in response:" + data.getResults().get(0).getOriginalTitle());
                }

                @Override
                public void onError(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(getApplicationContext(), "request failed!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
