package com.ifood.client;

import com.ifood.model.SpotifyAuthCredentials;
import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static feign.Util.toByteArray;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SpotifyClientCredentialsTest {

    private MockClient mockClient;
    private SpotifyClient spotifyClient;

    private final static String ENCODED_CREDENTIALS = "jfhfewk8439u4hgoi34y54ef";
    private final static String GRANT_TYPE = "jfhfewk8439u4hgoi34y54ef";
    private final static String DEFAULT_TOKEN = "abcd123456";

    @Before
    public void setUp() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("SpotifyClientCredentialsResponse.json")) {
            byte[] data = toByteArray(input);
            mockClient = new MockClient();
            spotifyClient = Feign.builder()
                    .decoder(new GsonDecoder())
                    .encoder(new FormEncoder())
                    .client(mockClient.ok(HttpMethod.POST, "/api/token", data))
                    .target(new MockTarget<>(SpotifyClient.class));
        }
    }

    @Test
    public void shouldReturnOkForGetClientCredentials() {
        SpotifyAuthCredentials credentials = spotifyClient.getSpotifyAuthCredentials(ENCODED_CREDENTIALS, GRANT_TYPE);
        assertThat(credentials, notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldReturnCorrectTokenHardcodedInJsonFile() {
        SpotifyAuthCredentials credentials = spotifyClient.getSpotifyAuthCredentials(ENCODED_CREDENTIALS, GRANT_TYPE);
        assertThat(credentials.getAccessToken(), is(DEFAULT_TOKEN));
        mockClient.verifyStatus();
    }

}
