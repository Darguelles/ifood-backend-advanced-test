package com.ifood.service;

import com.ifood.client.OpenWeatherClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Service;

import static com.ifood.model.enums.Measure.CELCIUS;

@Service
public class WeatherService {

    private OpenWeatherClient openWeatherClient;
    private AppProperties appProperties;

    public WeatherService(OpenWeatherClient openWeatherClient, AppProperties appProperties) {
        this.openWeatherClient = openWeatherClient;
        this.appProperties = appProperties;
    }

    public WeatherResponse getWeatherByCityName(String cityName) throws WeatherUndefinedException {
        WeatherResponse response = openWeatherClient.getCurrentWeatherByCityName(cityName,
                appProperties.getWeatherApplicationId(), CELCIUS.code());
        if (response == null) {
            throw new WeatherUndefinedException("No weather information for the current location");
        } else {
            return response;
        }
    }

    public WeatherResponse getWeatherByCityLocation(Long longitude, Long latitude) throws WeatherUndefinedException {
        WeatherResponse response = openWeatherClient.getCurrentWeatherByLocation(latitude, longitude,
                appProperties.getWeatherApplicationId(), CELCIUS.code());
        if (response == null) {
            throw new WeatherUndefinedException("No weather information for the current location");
        } else {
            return response;
        }
    }

}
