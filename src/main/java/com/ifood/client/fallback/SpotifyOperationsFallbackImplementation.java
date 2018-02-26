package com.ifood.client.fallback;

import com.ifood.client.SpotifyOperationsClient;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.TrackSearchResult;

public class SpotifyOperationsFallbackImplementation implements SpotifyOperationsClient {

    private final Throwable cause;

    public SpotifyOperationsFallbackImplementation(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public PlaylistSearchResult getPlaylistByCategory(String authorization, String categoryId) {
        return null;
    }

    @Override
    public TrackSearchResult getTracks(String authorization, String playlistId) {
        return null;
    }
}
