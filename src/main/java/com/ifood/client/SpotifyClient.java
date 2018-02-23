package com.ifood.client;

import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.Playlists;
import com.ifood.model.SpotifyAuthCredentials;
import com.ifood.model.TrackSearchResult;
import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;

public interface SpotifyClient {

    @RequestLine("POST /api/token")
    @Headers({"Authorization: Basic {encodedCredentials}", "Content-Type: application/x-www-form-urlencoded"})
    SpotifyAuthCredentials getSpotifyAuthCredentials(@Param("encodedCredentials") String encodedCredentials, @Param("grant_type") String grantType);

    @RequestLine("GET /v1/browse/categories/{categoryId}/playlists")
    @Headers("Authorization: {authorization}")
    PlaylistSearchResult getPlaylistByCategory(@Param("authorization") String authorization, @Param("categoryId") String categoryId);

    @RequestLine("GET /v1/users/spotify/playlists/{playlistId}/tracks")
    @Headers("Authorization: {authorization}")
    TrackSearchResult getTracks(@Param("authorization") String authorization, @Param("playlistId") String playlistId);

    static SpotifyClient connect(String host) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new FormEncoder())
                .target(SpotifyClient.class, host);
    }

}
