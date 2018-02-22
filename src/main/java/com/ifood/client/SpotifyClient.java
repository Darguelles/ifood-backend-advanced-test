package com.ifood.client;

import com.ifood.model.SpotifyAuthCredentials;
import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;

public interface SpotifyClient {

    @RequestLine("POST /api/token")
    @Headers({"Authorization: Basic {encodedCredentials}", "Content-Type: application/x-www-form-urlencoded"})
    SpotifyAuthCredentials getSpotifyAuthCredentials(@Param("encodedCredentials") String encodedCredentials, @Param("grant_type") String grantType);

    static SpotifyClient connect() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new FormEncoder())
                .target(SpotifyClient.class, "https://accounts.spotify.com");
    }

}
