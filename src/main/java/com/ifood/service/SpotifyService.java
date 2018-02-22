package com.ifood.service;

import com.ifood.client.SpotifyClient;
import com.ifood.model.SpotifyAuthCredentials;
import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private SpotifyClient spotifyClient = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new FormEncoder())
            .target(SpotifyClient.class, "https://accounts.spotify.com");

    public SpotifyAuthCredentials retrieveSpotifyClientCredentials(){
        return null;
    }
}
