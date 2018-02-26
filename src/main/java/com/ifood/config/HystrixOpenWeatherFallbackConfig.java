package com.ifood.config;

import com.ifood.client.OpenWeatherClient;
import com.ifood.client.fallback.OpenWeatherFallbackImplementation;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixOpenWeatherFallbackConfig implements FallbackFactory<OpenWeatherClient> {

    @Override
    public OpenWeatherClient create(Throwable cause) {
        return new OpenWeatherFallbackImplementation(cause);
    }
}
