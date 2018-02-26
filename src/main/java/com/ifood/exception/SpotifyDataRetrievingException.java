package com.ifood.exception;

public class SpotifyDataRetrievingException extends Exception {

    public SpotifyDataRetrievingException(String message) {
        super(message);
    }

    public SpotifyDataRetrievingException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
