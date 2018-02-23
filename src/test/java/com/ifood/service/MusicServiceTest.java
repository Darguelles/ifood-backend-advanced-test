package com.ifood.service;

import com.ifood.builders.SpotifyAuthCredentialsBuilder;
import com.ifood.builders.TrackSearchResultBuilder;
import com.ifood.model.*;
import com.ifood.model.util.Playlist;
import com.ifood.model.util.PlaylistsContainer;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MusicServiceTest {

    private final static String DEFAULT_LOCATION_NAME = "Lima";
    private final static String DEFAULT_PLAYLIST_ID = "123456";
    private final static Double DEFAULT_TEMP = 11.3;
    private final static String DEFAULT_CATEGORY = "rock";

    @Mock
    private SpotifyService spotifyService;

    @Mock
    private WeatherService weatherService;

    private MusicService musicService;
    private SpotifyAuthCredentials defaultCredentials;

    @Before
    public void setUp() throws Exception {
        defaultCredentials = SpotifyAuthCredentialsBuilder.build().now();
        musicService = new MusicService(spotifyService, weatherService);
    }

    @Test
    public void shouldRetrievePlaylistSongs() throws Exception {
        when(spotifyService.retrieveSpotifyClientCredentials()).thenReturn(defaultCredentials);
        when(spotifyService.getTracksByPlaylist(defaultCredentials, DEFAULT_PLAYLIST_ID)).thenReturn(TrackSearchResultBuilder.build().now());
        when(spotifyService.getPlaylistByCategory(defaultCredentials, DEFAULT_CATEGORY)).thenReturn(new PlaylistSearchResult(new PlaylistsContainer(asList(new Playlist(DEFAULT_PLAYLIST_ID)))));
        when(weatherService.getWeatherByCityName(DEFAULT_LOCATION_NAME)).thenReturn(new WeatherResponse(new MainWeatherData(DEFAULT_TEMP)));

        WeatherPlaylist result = musicService.retrievePlaylist(DEFAULT_LOCATION_NAME);

        assertThat(result.getSongList().size(), is(3));
    }
}
