package com.beastpotato.movieinformation.managers;

import com.beastpotato.movieinformation.Constants;
import com.beastpotato.movieinformation.endpoints.configendpointresponse.ConfigEndpointApiResponse;
import com.beastpotato.movieinformation.endpoints.configendpointresponse.Images;

/**
 * Created by Oleksiy on 2/21/2016.
 */
public class ImageManager {
    private static ImageManager ourInstance = new ImageManager();
    private Images imageInfo;

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        return ourInstance;
    }

    public void setup(ConfigEndpointApiResponse response) {
        imageInfo = response.getImages();
    }

    public String getPosterImageUrl(String imagePath) {
        String apiKeyParam = "&api_key=" + Constants.apiKey;
        return imageInfo.getBaseUrl() + imageInfo.getPosterSizes().get((imageInfo.getPosterSizes().size() - 1) / 2) + imagePath + apiKeyParam;
    }
}
