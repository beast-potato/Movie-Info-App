package com.beastpotato.movieinformation.endpoints;

import com.beastpotato.potato.api.Constants;
import com.beastpotato.potato.api.Endpoint;
import com.beastpotato.potato.api.HeaderParam;
import com.beastpotato.potato.api.UrlParam;
import com.beastpotato.potato.api.UrlPathParam;

/**
 * Created by Oleksiy on 2/18/2016.
 */
@Endpoint(httpMethod = Constants.Http.GET, relativeUrl = "/discover/{discover_type}", jsonExample = "{\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/razvUuLkF7CX4XsLyj02ksC0ayy.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        80,\n" +
        "        28,\n" +
        "        53\n" +
        "      ],\n" +
        "      \"id\": 260346,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Taken 3\",\n" +
        "      \"overview\": \"Ex-government operative Bryan Mills finds his life is shattered when he's falsely accused of a murder that hits close to home. As he's pursued by a savvy police inspector, Mills employs his particular set of skills to track the real killer and exact his unique brand of justice.\",\n" +
        "      \"release_date\": \"2015-01-09\",\n" +
        "      \"poster_path\": \"/c2SSjUVYawDUnQ92bmTqsZsPEiB.jpg\",\n" +
        "      \"popularity\": 11.737899,\n" +
        "      \"title\": \"Taken 3\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 6.2,\n" +
        "      \"vote_count\": 698\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 11544,\n" +
        "  \"total_results\": 230847\n" +
        "}")
public abstract class DiscoverMovieEndpoint {

    @UrlPathParam("discover_type")
    String discoverType;

    @UrlParam("api_key")
    String apiKey;

    @UrlParam("page")
    Integer page;

    @UrlParam("sort_by")
    String sortBy;

    @HeaderParam("Content-Type")
    String contentType;
}
