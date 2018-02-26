package com.ifood.model.util;

import com.ifood.model.Track;

import java.io.Serializable;

public class Item implements Serializable{

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
