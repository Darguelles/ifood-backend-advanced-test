package com.ifood.model;

public class WeatherResponse {

    private MainWeatherData main;

    public WeatherResponse() {}

    public WeatherResponse(MainWeatherData main) {
        this.main = main;
    }

    public MainWeatherData getMain() {
        return main;
    }

    public void setMain(MainWeatherData main) {
        this.main = main;
    }
}
