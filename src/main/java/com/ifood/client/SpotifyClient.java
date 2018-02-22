package com.ifood.client;

import com.ifood.model.SpotifyAuthCredentials;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface SpotifyClient {


    @RequestLine("POST /api/token")
    @Headers({"Authorization: Basic {encodedCredentials}", "Content-Type: application/x-www-form-urlencoded"})
    SpotifyAuthCredentials getSpotifyAuthCredentials(@Param("encodedCredentials") String encodedCredentials, @Param("grant_type") String grantType);

}
