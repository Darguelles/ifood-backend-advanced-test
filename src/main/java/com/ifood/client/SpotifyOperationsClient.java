package com.ifood.client;


import com.ifood.config.FeignClientConfig;
import com.ifood.config.HystrixSpotifyOperationsFallbackConfig;
import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.TrackSearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "spotifyOperationsClient", url = "${spotify.apiHost}", fallbackFactory = HystrixSpotifyOperationsFallbackConfig.class, configuration = FeignClientConfig.class)
public interface SpotifyOperationsClient {

    @RequestMapping(method = GET, value = "/v1/browse/categories/{categoryId}/playlists")
    PlaylistSearchResult getPlaylistByCategory(@RequestHeader("Authorization") String authorization, @PathVariable("categoryId") String categoryId);

    @RequestMapping(method = GET, value = "/v1/users/spotify/playlists/{playlistId}/tracks")
    TrackSearchResult getTracks(@RequestHeader("Authorization") String authorization, @PathVariable("playlistId") String playlistId);

}
