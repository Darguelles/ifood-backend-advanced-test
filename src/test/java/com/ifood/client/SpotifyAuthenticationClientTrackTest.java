package com.ifood.client;

import com.ifood.model.TrackSearchResult;
import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static feign.Util.toByteArray;
import static feign.mock.HttpMethod.GET;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SpotifyAuthenticationClientTrackTest {

    private MockClient mockClient;
    private SpotifyAuthenticationClient spotifyAuthenticationClient;

    private final static String DEFAULT_TOKEN = "abcd123456";
    private final static String DEFAULT_TRACK_NAME = "Contagious (Mercer Remix)";
    private final static String DEFAULT_PLAYLIST_ID = "37i9dQZF1DWUlZhYdX0uqM";

    @Before
    public void setUp() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("SpotifyClientTracksResponse.json")) {
            byte[] data = toByteArray(input);
            mockClient = new MockClient();
            spotifyAuthenticationClient = Feign.builder()
                    .decoder(new GsonDecoder())
                    .encoder(new FormEncoder())
                    .client(mockClient.ok(GET, "/v1/users/spotify/playlists/" + DEFAULT_PLAYLIST_ID + "/tracks", data))
                    .target(new MockTarget<>(SpotifyAuthenticationClient.class));
        }
    }

    @Test
    public void shouldReturnOkForGetPlaylistsTracks() {
        TrackSearchResult searchResult = spotifyAuthenticationClient.getTracks(DEFAULT_TOKEN, DEFAULT_PLAYLIST_ID);
        assertThat(searchResult, notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldMapRetrievedValuesWithCapplicationObjectModel() {
        TrackSearchResult searchResult = spotifyAuthenticationClient.getTracks(DEFAULT_TOKEN, DEFAULT_PLAYLIST_ID);
        assertThat(searchResult.getItems().get(0).getTrack().getName(), is(DEFAULT_TRACK_NAME));
        mockClient.verifyStatus();
    }
}
