package com.ifood.builders;

import com.ifood.model.util.Playlist;
import com.ifood.model.util.PlaylistsContainer;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class PlaylistContainerBuilder {

    public List<Playlist> playlists = asList(PlaylistBuilder.build().now());

    private PlaylistContainerBuilder() {
    }

    public static PlaylistContainerBuilder build() {
        return new PlaylistContainerBuilder();
    }

    public PlaylistsContainer now() {
        return new PlaylistsContainer(playlists);
    }

    public PlaylistContainerBuilder with(Consumer<PlaylistContainerBuilder> s) {
        s.accept(this);
        return this;
    }

}
