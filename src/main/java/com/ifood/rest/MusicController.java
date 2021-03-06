package com.ifood.rest;

import com.ifood.exception.SpotifyDataRetrievingException;
import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.WeatherPlaylist;
import com.ifood.service.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestController
public class MusicController {

    private MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping(value = "/playlist", params = "location")
    public ResponseEntity<WeatherPlaylist> getWeatherPlaylist(@RequestParam String location) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException, SpotifyDataRetrievingException {
        WeatherPlaylist result = musicService.retrievePlaylist(location);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/playlist", params = {"longitude", "latitude"})
    public ResponseEntity<WeatherPlaylist> getWeatherPlaylist(@RequestParam Double longitude, @RequestParam Double latitude) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException, SpotifyDataRetrievingException {
        WeatherPlaylist result = musicService.retrievePlaylist(latitude, longitude);
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(WeatherUndefinedException.class)
    public ResponseEntity openWeatherUnavailableExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity(SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({SpotifyDataRetrievingException.class, SpotifyUndefinedCredentialsException.class})
    public ResponseEntity spotifyUnavailableExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity(SERVICE_UNAVAILABLE);
    }

}
