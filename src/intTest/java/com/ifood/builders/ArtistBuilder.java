package com.ifood.builders;

import com.ifood.model.util.Artist;

import java.util.function.Consumer;

public class ArtistBuilder {

    public static final String DEFAULT_NAME = "Apetite for destruction";
    public static final String DEFAULT_URI = "www.gunsnroses.com";

    public String name = DEFAULT_NAME;
    public String uri = DEFAULT_URI;

    private ArtistBuilder() {
    }

    public static ArtistBuilder build() {
        return new ArtistBuilder();
    }

    public Artist now(){
        return new Artist(name, uri);
    }

    public ArtistBuilder with(Consumer<ArtistBuilder> s) {
        s.accept(this);
        return this;
    }
}
