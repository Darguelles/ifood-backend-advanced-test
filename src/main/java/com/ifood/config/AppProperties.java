package com.ifood.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties {

    @Value("${openweather.host}")
    private String weatherHost;
    @Value("${openweather.applicationId}")
    private String weatherApplicationId;
    @Value("${spotify.host}")
    private String spotifyHost;
    @Value("${spotify.clientId}")
    private String spotifyClientId;
    @Value("${spotify.clientSecret}")
    private String spotifyClientSecret;


    public String getWeatherApplicationId() {
        return weatherApplicationId;
    }

    public void setWeatherApplicationId(String weatherApplicationId) {
        this.weatherApplicationId = weatherApplicationId;
    }

    public String getWeatherHost() {
        return weatherHost;
    }

    public void setWeatherHost(String weatherHost) {
        this.weatherHost = weatherHost;
    }

    public String getSpotifyHost() {
        return spotifyHost;
    }

    public void setSpotifyHost(String spotifyHost) {
        this.spotifyHost = spotifyHost;
    }

    public String getSpotifyClientId() {
        return spotifyClientId;
    }

    public void setSpotifyClientId(String spotifyClientId) {
        this.spotifyClientId = spotifyClientId;
    }

    public String getSpotifyClientSecret() {
        return spotifyClientSecret;
    }

    public void setSpotifyClientSecret(String spotifyClientSecret) {
        this.spotifyClientSecret = spotifyClientSecret;
    }
}
