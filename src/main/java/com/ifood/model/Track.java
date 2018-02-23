package com.ifood.model;

import com.ifood.model.util.Album;
import com.ifood.model.util.Artist;

import java.util.List;

public class Track {

    private Album album;
    private List<Artist> artists;
    private String name;

    public Track() {
    }

    public Track(Album album, List<Artist> artists, String name) {
        this.album = album;
        this.artists = artists;
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
