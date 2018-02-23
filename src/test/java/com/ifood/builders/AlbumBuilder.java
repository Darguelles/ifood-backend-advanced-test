package com.ifood.builders;

import com.ifood.model.util.Album;

import java.util.function.Consumer;

public class AlbumBuilder {

    public static final String DEFAULT_NAME = "Apetite for destruction";
    public static final String DEFAULT_URI = "www.gunsnroses.com";

    public String name = DEFAULT_NAME;
    public String uri = DEFAULT_URI;

    private AlbumBuilder() {
    }

    public static AlbumBuilder build() {
        return new AlbumBuilder();
    }

    public Album now(){
        return new Album(name, uri);
    }

    public AlbumBuilder with(Consumer<AlbumBuilder> s) {
        s.accept(this);
        return this;
    }
}
