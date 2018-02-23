package com.ifood.service;

import com.ifood.client.OpenWeatherClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.WeatherResponse;
import org.springframework.stereotype.Service;

import static com.ifood.model.enums.Measure.CELCIUS;

@Service
public class WeatherService {

    private AppProperties appProperties;

    public WeatherService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public WeatherResponse getWeatherByCityName(String cityName) throws WeatherUndefinedException {
        OpenWeatherClient weatherClient = OpenWeatherClient.connect(appProperties.getWeatherHost());
        WeatherResponse response = weatherClient.getCurrentWeatherByCityName(cityName,
                appProperties.getWeatherApplicationId(), CELCIUS.code());
        if (response == null) {
            throw new WeatherUndefinedException("No weather information for the current location");
        } else {
            return response;
        }
    }

    public WeatherResponse getWeatherByCityLocation(Long longitude, Long latitude) throws WeatherUndefinedException {
        OpenWeatherClient weatherClient = OpenWeatherClient.connect(appProperties.getWeatherHost());
        WeatherResponse response = weatherClient.getCurrentWeatherByLocation(latitude, longitude,
                appProperties.getWeatherApplicationId(), CELCIUS.code());
        if (response == null) {
            throw new WeatherUndefinedException("No weather information for the current location");
        } else {
            return response;
        }
    }

}
