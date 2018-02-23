package com.ifood.service;

import com.ifood.client.SpotifyClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.model.*;
import com.ifood.repository.SpotifyAuthCredentialsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ifood.util.Util.getBase64EncodedString;

@Service
public class SpotifyService {

    private AppProperties appProperties;
    private SpotifyAuthCredentialsRepository credentialsRepository;
    private SpotifyClient spotifyClient;

    public SpotifyService(AppProperties appProperties, SpotifyAuthCredentialsRepository credentialsRepository) {
        this.appProperties = appProperties;
        this.credentialsRepository = credentialsRepository;
    }

    public SpotifyAuthCredentials retrieveSpotifyClientCredentials() throws SpotifyUndefinedCredentialsException {
        List<SpotifyAuthCredentials> savedCredentials = new ArrayList<>();
        credentialsRepository.findAll().forEach(savedCredentials::add);

        if (savedCredentials.size() > 0 && savedCredentials.get(0) != null) {
            System.out.println("getting credentials from storage");
            SpotifyAuthCredentials credentials = savedCredentials.get(0);
            return credentials;
        } else {
            System.out.println("generating new");
            return generateNewClientCredentials();
        }
    }

    private SpotifyAuthCredentials generateNewClientCredentials() throws SpotifyUndefinedCredentialsException {
        String encodcedClientCredentials = getBase64EncodedString(appProperties.getSpotifyClientId() + ":" + appProperties.getSpotifyClientSecret());
        spotifyClient = SpotifyClient.connect(appProperties.getSpotifyHost());
        SpotifyAuthCredentials credentials = spotifyClient.getSpotifyAuthCredentials(encodcedClientCredentials, "client_credentials");
        if (credentials == null) {
            throw new SpotifyUndefinedCredentialsException("Unable to retrieve client credentials. Verify client id/secret");
        } else {
            credentialsRepository.save(credentials);
            return credentials;
        }
    }

    public PlaylistSearchResult getPlaylistByCategory(SpotifyAuthCredentials credentials, String categoryId) throws SpotifyUndefinedCredentialsException {
        String token = credentials.getTokenType() + " " + credentials.getAccessToken();
        spotifyClient = SpotifyClient.connect(appProperties.getSpotifyApiHost());
        PlaylistSearchResult playlistsResult = spotifyClient.getPlaylistByCategory(token, categoryId);
        if (playlistsResult == null) {
            throw new SpotifyUndefinedCredentialsException("Unable to find playlist with the current credentials provided or category type");
        } else {
            return playlistsResult;
        }
    }

    public TrackSearchResult getTracksByPlaylist(SpotifyAuthCredentials credentials, String playlistId) throws SpotifyUndefinedCredentialsException {
        String token = credentials.getTokenType() + " " + credentials.getAccessToken();
        spotifyClient = SpotifyClient.connect(appProperties.getSpotifyApiHost());
        TrackSearchResult result = spotifyClient.getTracks(token, playlistId);
        if (result == null) {
            throw new SpotifyUndefinedCredentialsException("Unable to find playlist with the current credentials provided or category type");
        } else {
            return result;
        }
    }

}
