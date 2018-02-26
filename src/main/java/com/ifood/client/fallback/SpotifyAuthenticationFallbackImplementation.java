package com.ifood.client.fallback;

import com.ifood.client.SpotifyAuthenticationClient;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.SpotifyAuthCredentials;
import com.ifood.model.TrackSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SpotifyAuthenticationFallbackImplementation implements SpotifyAuthenticationClient {

    private static Logger LOGGER = LoggerFactory.getLogger(SpotifyAuthenticationFallbackImplementation.class);
    private final Throwable cause;

    public SpotifyAuthenticationFallbackImplementation(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public SpotifyAuthCredentials getSpotifyAuthCredentials(String encodedCredentials, Map<String, String> grantType) {
        LOGGER.info("Spotify service is not available: ", cause.getMessage());
        return null;
    }

}
