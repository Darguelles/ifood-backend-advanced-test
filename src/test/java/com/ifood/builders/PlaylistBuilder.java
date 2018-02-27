package com.ifood.builders;

import com.ifood.model.util.Playlist;

import java.util.function.Consumer;

public class PlaylistBuilder {

    public static final String DEFAULT_PLAYLIOST_ID = "ABCLJFKLDDKDSDDSD";

    public String id = DEFAULT_PLAYLIOST_ID;

    private PlaylistBuilder() {
    }

    public static PlaylistBuilder build() {
        return new PlaylistBuilder();
    }

    public Playlist now() {
        return new Playlist(id);
    }

    public PlaylistBuilder with(Consumer<PlaylistBuilder> s) {
        s.accept(this);
        return this;
    }
}
