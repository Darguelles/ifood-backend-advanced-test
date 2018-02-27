package com.ifood.service;

import com.ifood.builders.PlaylistSearchResultBuilder;
import com.ifood.builders.SpotifyAuthCredentialsBuilder;
import com.ifood.client.SpotifyAuthenticationClient;
import com.ifood.client.SpotifyOperationsClient;
import com.ifood.config.AppProperties;
import com.ifood.exception.SpotifyDataRetrievingException;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.SpotifyAuthCredentials;
import com.ifood.repository.SpotifyAuthCredentialsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static com.ifood.builders.SpotifyAuthCredentialsBuilder.DEFAULT_ACCESS_TOKEN;
import static com.ifood.builders.SpotifyAuthCredentialsBuilder.DEFAULT_TOKEN_TYPE;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyServiceTest {

    private final static String ENCODED_CREDENTIALS = "ODEwZDcxOTZmOWRkNGIxOWI3ZGNmM2MwN2QzZGJjODQ6YmI2ODNhYWY4ZjIzNGUyYTk3NzRlOWI2MGJmMmI2ZmE=";
    private final static String SPOTIFY_CLIENT_ID = "810d7196f9dd4b19b7dcf3c07d3dbc84";
    private final static String SPOTIFY_CLIENT_SECRET = "bb683aaf8f234e2a9774e9b60bf2b6fa";
    private final static String DEFAULT_TOKEN = DEFAULT_TOKEN_TYPE + " " + DEFAULT_ACCESS_TOKEN;
    private final static String DEFAULT_CATEGORY_ID = "rock";
    private Map<String, String> parameters = new HashMap<>();

    @Mock
    private SpotifyAuthenticationClient spotifyAuthenticationClient;

    @Mock
    private SpotifyOperationsClient spotifyOperationsClient;

    @Mock
    private AppProperties appProperties;

    @Mock
    private SpotifyAuthCredentialsRepository credentialsRepository;

    private SpotifyService spotifyService;

    @Before
    public void setUp() throws Exception {
        spotifyService = new SpotifyService(appProperties, credentialsRepository, spotifyAuthenticationClient, spotifyOperationsClient);
        parameters.put("grant_type", "client_credentials");
    }

    @Test
    public void shouldCreateNewSpotifyCredentialsIfNoOneIsPreviouslySaved() throws Exception {
        when(credentialsRepository.findAll()).thenReturn(null);
        when(spotifyAuthenticationClient.getSpotifyAuthCredentials("Basic " + ENCODED_CREDENTIALS, parameters)).thenReturn(SpotifyAuthCredentialsBuilder.build().now());
        when(appProperties.getSpotifyClientId()).thenReturn(SPOTIFY_CLIENT_ID);
        when(appProperties.getSpotifyClientSecret()).thenReturn(SPOTIFY_CLIENT_SECRET);

        SpotifyAuthCredentials credentials = spotifyService.retrieveSpotifyClientCredentials();

        assertThat(credentials.getAccessToken(), is(DEFAULT_ACCESS_TOKEN));
    }

    @Test
    public void shouldReturnCurrentSpotifyCredentialsIfOneIsPreviouslySaved() throws Exception {
        when(credentialsRepository.findAll()).thenReturn(asList(SpotifyAuthCredentialsBuilder.build().now()));

        SpotifyAuthCredentials credentials = spotifyService.retrieveSpotifyClientCredentials();

        assertThat(credentials.getAccessToken(), is(DEFAULT_ACCESS_TOKEN));
    }

    @Test
    public void shouldRetrievePlaylistbyCategory() throws Exception {
        when(spotifyOperationsClient.getPlaylistByCategory(DEFAULT_TOKEN, DEFAULT_CATEGORY_ID)).thenReturn(PlaylistSearchResultBuilder.build().now());

        PlaylistSearchResult result = spotifyService.getPlaylistByCategory(SpotifyAuthCredentialsBuilder.build().now(), DEFAULT_CATEGORY_ID);

        assertThat(result.getPlaylists().getItems().get(0), notNullValue());
    }

    @Test(expected = SpotifyDataRetrievingException.class)
    public void shouldThrowSpotifyDataRetrievingExceptionWhenPlaylistSearchResultIsNull() throws Exception {
        when(spotifyOperationsClient.getPlaylistByCategory(DEFAULT_TOKEN, DEFAULT_CATEGORY_ID)).thenReturn(null);

        spotifyService.getPlaylistByCategory(SpotifyAuthCredentialsBuilder.build().now(), DEFAULT_CATEGORY_ID);
    }
}
