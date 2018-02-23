package com.ifood.model.util;

import java.util.List;

public class PlaylistsContainer {

    private List<Playlist> items;

    public PlaylistsContainer() {
    }

    public PlaylistsContainer(List<Playlist> items) {
        this.items = items;
    }

    public List<Playlist> getItems() {
        return items;
    }

    public void setItems(List<Playlist> items) {
        this.items = items;
    }
}
