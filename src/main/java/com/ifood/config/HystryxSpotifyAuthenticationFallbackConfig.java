package com.ifood.config;

import com.ifood.client.SpotifyAuthenticationClient;
import com.ifood.client.fallback.SpotifyAuthenticationFallbackImplementation;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystryxSpotifyAuthenticationFallbackConfig implements FallbackFactory<SpotifyAuthenticationClient> {

    @Override
    public SpotifyAuthenticationClient create(Throwable cause) {
        return new SpotifyAuthenticationFallbackImplementation(cause);
    }

}
