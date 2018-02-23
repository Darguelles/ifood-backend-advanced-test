package com.ifood.model.util;

import com.ifood.model.Track;

public class Item {

    private Track track;

    public Item() {
    }

    public Item(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
