package com.ifood.service;

import com.ifood.client.SpotifyClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.model.SpotifyAuthCredentials;
import org.springframework.stereotype.Service;

import static com.ifood.util.Util.getBase64EncodedString;

@Service
public class SpotifyService {

    private AppProperties appProperties;
    private SpotifyClient spotifyClient = SpotifyClient.connect();

    public SpotifyService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public SpotifyAuthCredentials retrieveSpotifyClientCredentials() throws Exception {
        String encodcedClientCredentials = getBase64EncodedString(appProperties.getSpotifyClientId() + ":" + appProperties.getSpotifyClientSecret());
        SpotifyAuthCredentials credentials = spotifyClient.getSpotifyAuthCredentials(encodcedClientCredentials, "client_credentials");
        if (credentials == null) {
            throw new SpotifyUndefinedCredentialsException("Unable to retrieve client credentials. Verify client id/secret");
        } else {
            return credentials;
        }
    }

}
