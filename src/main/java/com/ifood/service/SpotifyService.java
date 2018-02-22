package com.ifood.service;

import com.ifood.client.SpotifyClient;
import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.model.SpotifyAuthCredentials;
import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Service;

import static com.ifood.util.Util.getBase64EncodedString;

@Service
public class SpotifyService {

    private SpotifyClient spotifyClient = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new FormEncoder())
            .target(SpotifyClient.class, "https://accounts.spotify.com");

    public SpotifyAuthCredentials retrieveSpotifyClientCredentials() throws Exception {
        String encodcedClientCredentials = getBase64EncodedString("810d7196f9dd4b19b7dcf3c07d3dbc84:bb683aaf8f234e2a9774e9b60bf2b6fa");
        SpotifyAuthCredentials credentials = spotifyClient.getSpotifyAuthCredentials(encodcedClientCredentials, "client_credentials");
        if (credentials == null) {
            throw new SpotifyUndefinedCredentialsException("Unable to retrieve client credentials. Verify the client id/secret");
        } else {
            return credentials;
        }
    }


}
