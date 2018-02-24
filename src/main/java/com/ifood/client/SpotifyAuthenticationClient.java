package com.ifood.client;

import com.ifood.config.FeignClientConfig;
import com.ifood.config.HystryxSpotifyAuthenticationFallbackConfig;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.SpotifyAuthCredentials;
import com.ifood.model.TrackSearchResult;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "spotifyAuthenticationClient", url = "${spotify.host}",fallbackFactory = HystryxSpotifyAuthenticationFallbackConfig.class, configuration = FeignClientConfig.class)
public interface SpotifyAuthenticationClient {

    @PostMapping(value = "/api/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    SpotifyAuthCredentials getSpotifyAuthCredentials(@RequestHeader("Authorization") String encodedCredentials,
                                                     @RequestParam Map<String, String> formParams);
}
