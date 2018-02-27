package com.ifood.client.fallback;

import com.ifood.client.OpenWeatherClient;
import com.ifood.model.WeatherResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenWeatherFallbackImplementation implements OpenWeatherClient {

    private static Logger LOGGER = LoggerFactory.getLogger(OpenWeatherFallbackImplementation.class);
    private final Throwable cause;

    public OpenWeatherFallbackImplementation(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public WeatherResponse getCurrentWeatherByLocation(Double latitude, Double longitude, String appId, String units) {
        LOGGER.info("Open weather service is not available: ", cause.getMessage());
        return null;
    }

    @Override
    public WeatherResponse getCurrentWeatherByCityName(String cityName, String appId, String units) {
        LOGGER.info("Open weather service is not available: ", cause.getMessage());
        return null;
    }
}
