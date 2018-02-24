package com.ifood.config;

import com.ifood.client.SpotifyOperationsClient;
import com.ifood.client.fallback.SpotifyOperationsFallbackImplementation;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixSpotifyOperationsFallbackConfig implements FallbackFactory<SpotifyOperationsClient> {

    @Override
    public SpotifyOperationsClient create(Throwable cause) {
        return new SpotifyOperationsFallbackImplementation(cause);
    }

}
