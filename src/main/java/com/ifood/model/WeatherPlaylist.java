package com.ifood.model;

import java.util.Set;

public class WeatherPlaylist {

    private Double currentTemperature;
    private String genre;
    private Set<Track> songList;

    public WeatherPlaylist() {
    }

    public WeatherPlaylist(Double currentTemperature, String genre, Set<Track> songList) {
        this.currentTemperature = currentTemperature;
        this.genre = genre;
        this.songList = songList;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Track> getSongList() {
        return songList;
    }

    public void setSongList(Set<Track> songList) {
        this.songList = songList;
    }
}
