package com.ifood.service;

import com.ifood.client.OpenWeatherClient;
import com.ifood.config.AppProperties;
import com.ifood.model.WeatherResponse;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Service;

import static com.ifood.model.enums.Measure.CELCIUS;

@Service
public class WeatherService {

    OpenWeatherClient weatherClient = Feign.builder()
            .decoder(new GsonDecoder())
            .target(OpenWeatherClient.class, "api.openweathermap.org");

    private AppProperties appProperties;

    public WeatherService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public WeatherResponse getWeatherByCityName(String cityName) {
        WeatherResponse response = weatherClient.getCurrentWeatherByCityName(cityName,
                appProperties.getWeatherApplicationId(), CELCIUS.code());
        return response;
    }

}
