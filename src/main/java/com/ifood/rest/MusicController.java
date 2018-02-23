package com.ifood.rest;

import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.Track;
import com.ifood.model.WeatherPlaylist;
import com.ifood.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MusicController {

    MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/playlist/{locationName}")
    public WeatherPlaylist getWeatherPlaylist(@PathVariable String locationName) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException {
        return musicService.retrievePlaylist(locationName);
    }

    @GetMapping("/playlist/{locationLong}/{locationLat}")
    public WeatherPlaylist getWeatherPlaylist(@PathVariable Long locationLong, @PathVariable Long locationLat) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException  {
        return musicService.retrievePlaylist(locationLong, locationLat);
    }

}
