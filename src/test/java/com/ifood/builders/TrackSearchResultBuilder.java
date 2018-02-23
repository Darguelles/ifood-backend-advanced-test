package com.ifood.builders;

import com.ifood.model.TrackSearchResult;
import com.ifood.model.util.Item;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TrackSearchResultBuilder {

    public List<Item> items = Arrays.asList(new Item(TrackBuilder.build().now()),
            new Item(TrackBuilder.build().with(track -> track.name = "Don't cry").now()),
            new Item(TrackBuilder.build().with(track -> track.name = "Paradise City").now()));

    private TrackSearchResultBuilder() {
    }

    public static TrackSearchResultBuilder build() {
        return new TrackSearchResultBuilder();
    }

    public TrackSearchResult now() {
        return new TrackSearchResult(items);
    }

    public TrackSearchResultBuilder with(Consumer<TrackSearchResultBuilder> s) {
        s.accept(this);
        return this;
    }

}
