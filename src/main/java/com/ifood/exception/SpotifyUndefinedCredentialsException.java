package com.ifood.exception;

public class SpotifyUndefinedCredentialsException extends Exception {

    public SpotifyUndefinedCredentialsException(String message) {
        super(message);
    }

    public SpotifyUndefinedCredentialsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
