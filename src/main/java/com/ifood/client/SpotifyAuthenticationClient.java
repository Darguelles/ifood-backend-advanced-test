package com.ifood.client;

import com.ifood.config.FeignClientConfig;
import com.ifood.config.HystryxSpotifyAuthenticationFallbackConfig;
import com.ifood.model.SpotifyAuthCredentials;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "spotifyAuthenticationClient", url = "${spotify.host}", fallbackFactory = HystryxSpotifyAuthenticationFallbackConfig.class, configuration = FeignClientConfig.class)
public interface SpotifyAuthenticationClient {

    @RequestMapping(method = POST, value = "/api/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    SpotifyAuthCredentials getSpotifyAuthCredentials(@RequestHeader("Authorization") String encodedCredentials,
                                                     @RequestParam Map<String, String> formParams);
}
