package com.ifood.client;

import com.ifood.model.PlaylistSearchResult;
import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.io.IOException;
import java.io.InputStream;

import static feign.Util.toByteArray;
import static feign.mock.HttpMethod.GET;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SpotifyAuthenticationClientPlaylistTest {

    private MockClient mockClient;
    private SpotifyOperationsClient spotifyOperationsClient;

    private final static String DEFAULT_TOKEN = "abcd123456";
    private final static String DEFAULT_CATEGORY = "rock";
    private final static String DEFAULT_PLAYLIST_ID = "37i9dQZF1DWUlZhYdX0uqM";

    @Before
    public void setUp() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("SpotifyClientPlaylistsResponse.json")) {
            byte[] data = toByteArray(input);
            mockClient = new MockClient();
            spotifyOperationsClient = Feign.builder()
                    .contract(new SpringMvcContract())
                    .decoder(new GsonDecoder())
                    .encoder(new FormEncoder())
                    .client(mockClient.ok(GET, "/v1/browse/categories/rock/playlists", data))
                    .target(new MockTarget<>(SpotifyOperationsClient.class));
        }
    }

    @Test
    public void shouldReturnOkForGetCategoryPlaylists() {
        PlaylistSearchResult searchResult = spotifyOperationsClient.getPlaylistByCategory(DEFAULT_TOKEN, DEFAULT_CATEGORY);
        assertThat(searchResult, notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldMapCorrectlyRetrievedValuesWithOurObjectModel() {
        PlaylistSearchResult searchResult = spotifyOperationsClient.getPlaylistByCategory(DEFAULT_TOKEN, DEFAULT_CATEGORY);
        assertThat(searchResult.getPlaylists().getItems().get(0).getId(), is(DEFAULT_PLAYLIST_ID));
        mockClient.verifyStatus();
    }

}
