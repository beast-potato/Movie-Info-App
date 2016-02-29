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

    @Validation(fieldName = "firstPathPart")
    public static boolean isFirstPathPartValid(String pathPart) {
        return pathPart != null;
    }

    @Validation(fieldName = "secondPathPart")
    public static boolean isSecondPathPartValid(String pathPart) {
        return pathPart != null;
    }
}
