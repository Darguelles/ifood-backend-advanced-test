package com.ifood.model;

import com.ifood.model.util.PlaylistsContainer;

public class PlaylistSearchResult {

    private PlaylistsContainer playlists;

    public PlaylistSearchResult() {
    }

    public PlaylistSearchResult(PlaylistsContainer playlists) {
        this.playlists = playlists;
    }

    public PlaylistsContainer getPlaylists() {
        return playlists;
    }

    public void setPlaylists(PlaylistsContainer playlists) {
        this.playlists = playlists;
    }
}
