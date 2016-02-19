package com.beastpotato.movieinformation.utils;

import com.beastpotato.potato.api.Validation;

/**
 * Created by Oleksiy on 2/18/2016.
 */
public class Validator {

    @Validation(fieldName = "api_key")
    public static boolean isApiKeyValid(String key) {
        return key != null;
    }

    @Validation(fieldName = "page")
    public static boolean isPageNumberValid(Integer page) {
        return page >= 1 && page <= 1000;
    }

    @Validation(fieldName = "discover_type")
    public static boolean isDiscoveryTypeValid(String discoveryStr) {
        return discoveryStr != null && (discoveryStr.equals("tv") || discoveryStr.equals("movie"));
    }
}
