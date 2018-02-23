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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MusicControllerTest {

    private final static String DEFAULT_LOCATION_NAME = "Lima";
    private final static Long DEFAULT_LOCATION_LONGITUDE = 344l;
    private final static Long DEFAULT_LOCATION_LATITUDE = 4324l;

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

        WeatherPlaylist response = musicController.getWeatherPlaylist(DEFAULT_LOCATION_NAME);

        assertThat(response.getSongList().size(), is(3));
    }

    @Test
    public void shouldReturnWeatherTrackListByLocationCoord() throws Exception {
        when(musicService.retrievePlaylist(DEFAULT_LOCATION_LONGITUDE, DEFAULT_LOCATION_LATITUDE)).thenReturn(WeatherPlaylistBuilder.build().now());

        WeatherPlaylist response = musicController.getWeatherPlaylist(DEFAULT_LOCATION_LONGITUDE, DEFAULT_LOCATION_LATITUDE);

        assertThat(response.getSongList().size(), is(3));
    }
}
