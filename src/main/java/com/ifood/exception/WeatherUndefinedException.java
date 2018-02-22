package com.ifood.exception;

public class WeatherUndefinedException extends Exception {

    public WeatherUndefinedException(String message) {
        super(message);
    }

    public WeatherUndefinedException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
