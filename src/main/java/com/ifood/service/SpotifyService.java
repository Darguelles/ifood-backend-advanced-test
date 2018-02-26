package com.ifood.service;

import com.ifood.client.SpotifyAuthenticationClient;
import com.ifood.client.SpotifyOperationsClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.SpotifyDataRetrievingException;
import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.SpotifyAuthCredentials;
import com.ifood.model.TrackSearchResult;
import com.ifood.repository.SpotifyAuthCredentialsRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ifood.util.Util.getBase64EncodedString;

@Service
public class SpotifyService {

    private AppProperties appProperties;
    private SpotifyAuthCredentialsRepository credentialsRepository;
    private SpotifyAuthenticationClient spotifyAuthenticationClient;
    private SpotifyOperationsClient spotifyOperationsClient;

    public SpotifyService(AppProperties appProperties, SpotifyAuthCredentialsRepository credentialsRepository, SpotifyAuthenticationClient spotifyAuthenticationClient, SpotifyOperationsClient spotifyOperationsClient) {
        this.appProperties = appProperties;
        this.credentialsRepository = credentialsRepository;
        this.spotifyAuthenticationClient = spotifyAuthenticationClient;
        this.spotifyOperationsClient = spotifyOperationsClient;
    }

    public SpotifyAuthCredentials retrieveSpotifyClientCredentials() throws SpotifyUndefinedCredentialsException {
        List<SpotifyAuthCredentials> savedCredentials = new ArrayList<>();
        credentialsRepository.findAll().forEach(savedCredentials::add);

        if (savedCredentials.size() > 0 && savedCredentials.get(0) != null) {
            SpotifyAuthCredentials credentials = savedCredentials.get(0);
            return credentials;
        } else {
            return generateNewClientCredentials();
        }
    }

    private SpotifyAuthCredentials generateNewClientCredentials() throws SpotifyUndefinedCredentialsException {
        String encodcedClientCredentials = getBase64EncodedString(appProperties.getSpotifyClientId() + ":" + appProperties.getSpotifyClientSecret());
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("grant_type", "client_credentials");
        SpotifyAuthCredentials credentials = spotifyAuthenticationClient.getSpotifyAuthCredentials("Basic " + encodcedClientCredentials,
                requestParams);
        if (credentials == null) {
            throw new SpotifyUndefinedCredentialsException("Unable to retrieve client credentials. Verify client id/secret");
        } else {
            credentialsRepository.save(credentials);
            return credentials;
        }
    }

    public PlaylistSearchResult getPlaylistByCategory(SpotifyAuthCredentials credentials, String categoryId) throws SpotifyDataRetrievingException {
        String token = credentials.getTokenType() + " " + credentials.getAccessToken();
        PlaylistSearchResult playlistsResult = spotifyOperationsClient.getPlaylistByCategory(token, categoryId);
        if (playlistsResult == null) {
            throw new SpotifyDataRetrievingException("Unable to find playlist with the current credentials provided or category type");
        } else {
            return playlistsResult;
        }
    }


    @Cacheable(cacheNames = "tracksearch", key = "{#credentials?.accessToken, #playlistId}")
    public TrackSearchResult getTracksByPlaylist(SpotifyAuthCredentials credentials, String playlistId) throws SpotifyDataRetrievingException {
        String token = credentials.getTokenType() + " " + credentials.getAccessToken();
        TrackSearchResult result = spotifyOperationsClient.getTracks(token, playlistId);
        if (result == null) {
            throw new SpotifyDataRetrievingException("Unable to find playlist with the current credentials provided or category type");
        } else {
            return result;
        }
    }

}
