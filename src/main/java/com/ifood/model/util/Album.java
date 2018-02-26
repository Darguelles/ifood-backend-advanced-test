package com.ifood.model.util;

import java.io.Serializable;

public class Album implements Serializable{

    private String name;
    private String uri;

    public Album() {
    }

    public Album(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
