package com.ifood.client;


import com.ifood.config.FeignClientConfig;
import com.ifood.config.HystrixSpotifyOperationsFallbackConfig;
import com.ifood.config.HystryxSpotifyAuthenticationFallbackConfig;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.TrackSearchResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "spotifyOperationsClient", url = "${spotify.apiHost}",fallbackFactory = HystrixSpotifyOperationsFallbackConfig.class, configuration = FeignClientConfig.class)
public interface SpotifyOperationsClient {

    @GetMapping("/v1/browse/categories/{categoryId}/playlists")
    PlaylistSearchResult getPlaylistByCategory(@RequestHeader("Authorization") String authorization, @PathVariable("categoryId") String categoryId);

    @GetMapping("/v1/users/spotify/playlists/{playlistId}/tracks")
    TrackSearchResult getTracks(@RequestHeader("Authorization") String authorization, @PathVariable("playlistId") String playlistId);

}
