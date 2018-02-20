package com.ifood.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties {

    @Value("${openweather.applicationId}")
    private String weatherApplicationId;

    public String getWeatherApplicationId() {
        return weatherApplicationId;
    }

    public void setWeatherApplicationId(String weatherApplicationId) {
        this.weatherApplicationId = weatherApplicationId;
    }
}
