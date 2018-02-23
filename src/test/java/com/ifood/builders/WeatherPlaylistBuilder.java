package com.ifood.builders;

import com.ifood.model.Track;
import com.ifood.model.WeatherPlaylist;
import com.ifood.model.util.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class WeatherPlaylistBuilder {

    public static final Double DEFAULT_CURRENT_TEMP = 22.4;
    public static final String DEFAULT_GENRE = "rock";

    private Double currentTemperature = DEFAULT_CURRENT_TEMP;
    private String genre = DEFAULT_GENRE;
    private Set<Track> songList = new HashSet<>(Arrays.asList(TrackBuilder.build().now(),
            TrackBuilder.build().with(track -> track.name = "Don't cry").now(),
            TrackBuilder.build().with(track -> track.name = "Paradise City").now()));

    private WeatherPlaylistBuilder() {
    }

    public static WeatherPlaylistBuilder build() {
        return new WeatherPlaylistBuilder();
    }

    public WeatherPlaylist now() {
        return new WeatherPlaylist(currentTemperature, genre, songList);
    }

    public WeatherPlaylistBuilder with(Consumer<WeatherPlaylistBuilder> s) {
        s.accept(this);
        return this;
    }

}
