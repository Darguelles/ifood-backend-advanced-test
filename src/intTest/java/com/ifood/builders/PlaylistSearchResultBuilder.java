package com.ifood.builders;

import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.util.PlaylistsContainer;

import java.util.function.Consumer;

public class PlaylistSearchResultBuilder {

    public PlaylistsContainer playlistsContainer = PlaylistContainerBuilder.build().now();

    private PlaylistSearchResultBuilder() {
    }

    public static PlaylistSearchResultBuilder build() {
        return new PlaylistSearchResultBuilder();
    }

    public PlaylistSearchResult now() {
        return new PlaylistSearchResult(playlistsContainer);
    }

    public PlaylistSearchResultBuilder with(Consumer<PlaylistSearchResultBuilder> s) {
        s.accept(this);
        return this;
    }

}
