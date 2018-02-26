package com.ifood.model;

import com.ifood.model.util.Item;

import java.io.Serializable;
import java.util.List;

public class TrackSearchResult implements Serializable {

    private List<Item> items;

    public TrackSearchResult() {
    }

    public TrackSearchResult(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
