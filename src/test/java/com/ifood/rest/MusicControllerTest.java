package com.ifood.rest;

import com.ifood.builders.WeatherPlaylistBuilder;
import com.ifood.model.WeatherPlaylist;
import com.ifood.service.MusicService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MusicControllerTest {

    private final static String DEFAULT_LOCATION_NAME = "Lima";
    private final static Double DEFAULT_LOCATION_LONGITUDE = 34.4;
    private final static Double DEFAULT_LOCATION_LATITUDE = 432.4;

    @Mock
    private MusicService musicService;

    private MusicController musicController;

    @Before
    public void setUp() throws Exception {
        musicController = new MusicController(musicService);
    }

    @Test
    public void shouldReturnWeatherTrackListByCityName() throws Exception {
        when(musicService.retrievePlaylist(DEFAULT_LOCATION_NAME)).thenReturn(WeatherPlaylistBuilder.build().now());

        ResponseEntity<WeatherPlaylist> response = musicController.getWeatherPlaylist(DEFAULT_LOCATION_NAME);

        assertThat(response.getBody().getSongList().size(), is(3));
    }

    @Test
    public void shouldReturnWeatherTrackListByLocationCoord() throws Exception {
        when(musicService.retrievePlaylist(DEFAULT_LOCATION_LATITUDE, DEFAULT_LOCATION_LONGITUDE)).thenReturn(WeatherPlaylistBuilder.build().now());

        ResponseEntity<WeatherPlaylist> response = musicController.getWeatherPlaylist(DEFAULT_LOCATION_LONGITUDE, DEFAULT_LOCATION_LATITUDE);

        assertThat(response.getBody().getSongList().size(), is(3));
    }
}
