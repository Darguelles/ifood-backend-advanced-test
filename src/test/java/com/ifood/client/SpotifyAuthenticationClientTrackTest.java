package com.ifood.client;

import com.ifood.model.PlaylistSearchResult;
import com.ifood.model.TrackSearchResult;
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

public class SpotifyAuthenticationClientTrackTest {

    private MockClient mockClient;
    private SpotifyOperationsClient spotifyOperationsClient;

    private final static String DEFAULT_TOKEN = "abcd123456";
    private final static String DEFAULT_PLAYLIST_ID = "37i9dQZF1DWUlZhYdX0uqM";

    @Before
    public void setUp() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("SpotifyClientTracksResponse.json")) {
            byte[] data = toByteArray(input);
            mockClient = new MockClient();
            spotifyOperationsClient = Feign.builder()
                    .contract(new SpringMvcContract())
                    .decoder(new GsonDecoder())
                    .encoder(new FormEncoder())
                    .client(mockClient.ok(GET, "/v1/users/spotify/playlists/" + DEFAULT_PLAYLIST_ID + "/tracks", data))
                    .target(new MockTarget<>(SpotifyOperationsClient.class));
        }
    }

    @Test
    public void shouldReturnOkForGetCategoryPlaylists() {
        TrackSearchResult searchResult = spotifyOperationsClient.getTracks(DEFAULT_TOKEN, DEFAULT_PLAYLIST_ID);
        assertThat(searchResult, notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldMapCorrectlyRetrievedValuesWithOurObjectModel() {
        TrackSearchResult searchResult = spotifyOperationsClient.getTracks(DEFAULT_TOKEN, DEFAULT_PLAYLIST_ID);
        assertThat(searchResult.getItems().get(0).getTrack().getName(), notNullValue());
        mockClient.verifyStatus();
    }

}
