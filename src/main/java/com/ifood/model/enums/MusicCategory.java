package com.ifood.model.enums;

public enum MusicCategory {

    PARTY(30, "party"),
    POP(15, "pop"),
    ROCK(10, "rock"),
    CLASSIC(0, "classical");

    private Integer degree;
    private String searchKey;

    MusicCategory(Integer degree, String searchKey) {
        this.degree = degree;
        this.searchKey = searchKey;
    }

    public Integer getDegree() {
        return degree;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public static MusicCategory selectCategory(Double temp) {
        if (temp > PARTY.getDegree()) {
            return PARTY;
        } else if (temp > POP.getDegree()) {
            return POP;
        } else if (temp > ROCK.getDegree()) {
            return ROCK;
        } else {
            return CLASSIC;
        }
    }

}
