package com.ifood.service;

import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.ifood.model.enums.MusicCategory.selectCategory;

@Service
public class MusicService {

    private SpotifyService spotifyService;
    private WeatherService weatherService;

    public MusicService(SpotifyService spotifyService, WeatherService weatherService) {
        this.spotifyService = spotifyService;
        this.weatherService = weatherService;
    }

    public List retrievePlaylist(String locationName) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException {
        WeatherResponse weather = weatherService.getWeatherByCityName(locationName);
        List playList = retrievePlaylistSongs(weather.getMain().getTemp());
        return playList;
    }

    public List retrievePlaylist(Long longitude, Long latitude) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException {
        WeatherResponse weather = weatherService.getWeatherByCityLocation(longitude, latitude);
        Set<Track> songs = new TreeSet<>();
        List<Track> playList = retrievePlaylistSongs(weather.getMain().getTemp());
        return playList;
    }

    private List<Track> retrievePlaylistSongs(Double temperature) throws SpotifyUndefinedCredentialsException {
        SpotifyAuthCredentials credentials = spotifyService.retrieveSpotifyClientCredentials();
        Playlists playlists = spotifyService.getPlaylistByCategory(credentials, selectCategory(temperature).getSearchKey()).getPlaylists();
        List<Track> result = searchTracksByPlaylist(credentials, playlists);
        return result;
    }

    private List<Track> searchTracksByPlaylist(SpotifyAuthCredentials credentials, Playlists playlists) throws SpotifyUndefinedCredentialsException {
        List<Track> result = new ArrayList<>();
        for (Playlist playlist : playlists.getItems()) {
            TrackSearchResult tracksByPlaylist = spotifyService.getTracksByPlaylist(credentials, playlist.getId());
            tracksByPlaylist.getItems().forEach(item -> {
                result.add(item.getTrack());
            });
        }
        return result;
    }


}
