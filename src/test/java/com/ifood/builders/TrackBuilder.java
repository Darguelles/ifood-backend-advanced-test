package com.ifood.builders;

import com.ifood.model.Track;
import com.ifood.model.util.Album;
import com.ifood.model.util.Artist;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class TrackBuilder {

    public static final String DEFAULT_NAME = "Welcome to the Jungle";

    public Album album = AlbumBuilder.build().now();
    public List<Artist> artists = asList(ArtistBuilder.build().now());
    public String name = DEFAULT_NAME;

    private TrackBuilder() {
    }

    public static TrackBuilder build() {
        return new TrackBuilder();
    }

    public Track now() {
        return new Track(album, artists, name);
    }

    public TrackBuilder with(Consumer<TrackBuilder> s) {
        s.accept(this);
        return this;
    }

}
