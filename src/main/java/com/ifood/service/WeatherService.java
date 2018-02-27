package com.ifood.service;

import com.ifood.client.OpenWeatherClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.WeatherResponse;
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

    public void setOpenWeatherClient(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
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

    public WeatherResponse getWeatherByCityLocation(Double latitude, Double longitude) throws WeatherUndefinedException {
        WeatherResponse response = openWeatherClient.getCurrentWeatherByLocation(latitude, longitude,
                appProperties.getWeatherApplicationId(), CELCIUS.code());
        if (response == null) {
            throw new WeatherUndefinedException("No weather information for the current location");
        } else {
            return response;
        }
    }

}
