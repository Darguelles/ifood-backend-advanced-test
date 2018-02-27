package com.ifood.service;

import com.ifood.exception.SpotifyDataRetrievingException;
import com.ifood.exception.SpotifyUndefinedCredentialsException;
import com.ifood.exception.WeatherUndefinedException;
import com.ifood.model.*;
import com.ifood.model.util.Playlist;
import com.ifood.model.util.PlaylistsContainer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ifood.model.enums.MusicCategory.selectCategory;

@Service
public class MusicService {

    private SpotifyService spotifyService;
    private WeatherService weatherService;

    public MusicService(SpotifyService spotifyService, WeatherService weatherService) {
        this.spotifyService = spotifyService;
        this.weatherService = weatherService;
    }

    public WeatherPlaylist retrievePlaylist(String locationName) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException, SpotifyDataRetrievingException {
        WeatherResponse weather = weatherService.getWeatherByCityName(locationName);
        SpotifyAuthCredentials credentials = spotifyService.retrieveSpotifyClientCredentials();

        Set<Track> songs = new HashSet<>();
        songs.addAll(retrievePlaylistSongs(credentials, weather.getMain().getTemp()));
        WeatherPlaylist weatherPlaylist = new WeatherPlaylist(weather.getMain().getTemp(),
                selectCategory(weather.getMain().getTemp()).toString(), songs);
        return weatherPlaylist;
    }

    public WeatherPlaylist retrievePlaylist(Double latitude, Double longitude) throws WeatherUndefinedException, SpotifyUndefinedCredentialsException, SpotifyDataRetrievingException {
        WeatherResponse weather = weatherService.getWeatherByCityLocation(latitude, longitude);
        SpotifyAuthCredentials credentials = spotifyService.retrieveSpotifyClientCredentials();

        Set<Track> songs = new HashSet<>();
        songs.addAll(retrievePlaylistSongs(credentials, weather.getMain().getTemp()));
        WeatherPlaylist weatherPlaylist = new WeatherPlaylist(weather.getMain().getTemp(),
                selectCategory(weather.getMain().getTemp()).toString(), songs);
        return weatherPlaylist;
    }

    private List<Track> retrievePlaylistSongs(SpotifyAuthCredentials credentials, Double temperature) throws SpotifyDataRetrievingException, SpotifyUndefinedCredentialsException {
        PlaylistsContainer playlistsContainer = null;
        playlistsContainer = spotifyService.getPlaylistByCategory(credentials, selectCategory(temperature).getSearchKey()).getPlaylists();
        List<Track> result = searchTracksByPlaylist(credentials, playlistsContainer);
        return result;
    }

    private List<Track> searchTracksByPlaylist(SpotifyAuthCredentials credentials, PlaylistsContainer playlistsContainer) throws SpotifyUndefinedCredentialsException, SpotifyDataRetrievingException {
        List<Track> result = new ArrayList<>();
        for (Playlist playlist : playlistsContainer.getItems()) {
            TrackSearchResult tracksByPlaylist = spotifyService.getTracksByPlaylist(credentials, playlist.getId());
            if (tracksByPlaylist != null && tracksByPlaylist.getItems() != null) {
                tracksByPlaylist.getItems().forEach(item -> {
                    result.add(item.getTrack());
                });
            }
        }
        return result;
    }


}
