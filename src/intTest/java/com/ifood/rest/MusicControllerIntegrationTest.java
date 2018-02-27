package com.ifood.rest;


import com.ifood.IfoodMusicApplication;
import com.ifood.builders.PlaylistBuilder;
import com.ifood.builders.PlaylistSearchResultBuilder;
import com.ifood.builders.SpotifyAuthCredentialsBuilder;
import com.ifood.builders.TrackSearchResultBuilder;
import com.ifood.client.OpenWeatherClient;
import com.ifood.client.SpotifyAuthenticationClient;
import com.ifood.client.SpotifyOperationsClient;
import com.ifood.model.MainWeatherData;
import com.ifood.model.WeatherResponse;
import com.ifood.model.enums.Measure;
import com.ifood.repository.SpotifyAuthCredentialsRepository;
import com.ifood.service.SpotifyService;
import com.ifood.service.WeatherService;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static com.ifood.model.enums.MusicCategory.POP;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IfoodMusicApplication.class, webEnvironment = RANDOM_PORT)
public class MusicControllerIntegrationTest {

    private final static String DEFAULT_SPOTIFY_ENCODED_CREDENTIALS = "Basic ODEwZDcxOTZmOWRkNGIxOWI3ZGNmM2MwN2QzZGJjODQ6YmI2ODNhYWY4ZjIzNGUyYTk3NzRlOWI2MGJmMmI2ZmE=";
    private final static String DEFAULT_LOCATION_NAME = "lima";
    private final static String DEFAULT_OPEN_WEATHER_APP_ID = "8bda43274992b81b2e3bd399316b74dd";
    private final static Double DEFAULT_LATITUDE = -46.633309;
    private final static Double DEFAULT_LONGITUDE = -23.550520;
    private final static Double DEFAULT_WEATHER_TEMP = 16.9;

    @Mock
    private SpotifyOperationsClient spotifyOperationsClient;

    @Mock
    private SpotifyAuthenticationClient spotifyAuthenticationClient;

    @Mock
    private OpenWeatherClient openWeatherClient;

    @Mock
    private SpotifyAuthCredentialsRepository credentialsRepository;

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private WeatherService weatherService;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        spotifyService.setSpotifyAuthenticationClient(spotifyAuthenticationClient);
        spotifyService.setSpotifyOperationsClient(spotifyOperationsClient);
        spotifyService.setCredentialsRepository(credentialsRepository);
        weatherService.setOpenWeatherClient(openWeatherClient);
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnTrackListByLocationName() throws Exception {
        mockRestCallForRetrieveWeatherInfoByLocationName();
        mockRestCallForRetrieveSpotifyUserCredentials();
        mockRestCallForRetrieveSpotifyPlaylist();
        mockRestCallForRetrieveSpotifyTracks();
        mockRedisStoreServiceForSpotifyCredentials();

        given()
                .queryParam("location", DEFAULT_LOCATION_NAME)
        .when()
                .get("/playlist")
        .then()
                .statusCode(200)
                .body("currentTemperature", equalTo(16.9f));
    }

    @Test
    public void shouldReturnTrackListByLatitudeAndLongitude() throws Exception {
        mockRestCallForRetrieveWeatherInfoByLongitudeAndLatitude();
        mockRestCallForRetrieveSpotifyUserCredentials();
        mockRestCallForRetrieveSpotifyPlaylist();
        mockRestCallForRetrieveSpotifyTracks();
        mockRedisStoreServiceForSpotifyCredentials();

        given()
                .queryParam("longitude", DEFAULT_LONGITUDE)
                .queryParam("latitude", DEFAULT_LATITUDE)
        .when()
                .get("/playlist")
        .then()
                .statusCode(200)
                .body("currentTemperature", equalTo(16.9f));
    }


    private void mockRestCallForRetrieveSpotifyUserCredentials() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("grant_type", "client_credentials");
        when(spotifyAuthenticationClient.getSpotifyAuthCredentials(DEFAULT_SPOTIFY_ENCODED_CREDENTIALS, requestParams))
                .thenReturn(SpotifyAuthCredentialsBuilder.build().now());
    }

    private void mockRestCallForRetrieveWeatherInfoByLocationName() {
        when(openWeatherClient.getCurrentWeatherByCityName(DEFAULT_LOCATION_NAME, DEFAULT_OPEN_WEATHER_APP_ID, Measure.CELCIUS.code()))
                .thenReturn(new WeatherResponse(new MainWeatherData(DEFAULT_WEATHER_TEMP)));
    }

    private void mockRestCallForRetrieveWeatherInfoByLongitudeAndLatitude() {
        when(openWeatherClient.getCurrentWeatherByLocation(DEFAULT_LATITUDE, DEFAULT_LONGITUDE, DEFAULT_OPEN_WEATHER_APP_ID, Measure.CELCIUS.code()))
                .thenReturn(new WeatherResponse(new MainWeatherData(DEFAULT_WEATHER_TEMP)));
    }

    private void mockRestCallForRetrieveSpotifyPlaylist() {
        when(spotifyOperationsClient.getPlaylistByCategory("Bearer "+SpotifyAuthCredentialsBuilder.DEFAULT_ACCESS_TOKEN, POP.getSearchKey()))
                .thenReturn(PlaylistSearchResultBuilder.build().now());
    }

    private void mockRestCallForRetrieveSpotifyTracks() {
        when(spotifyOperationsClient.getTracks(SpotifyAuthCredentialsBuilder.DEFAULT_ACCESS_TOKEN, PlaylistBuilder.DEFAULT_PLAYLIOST_ID))
                .thenReturn(TrackSearchResultBuilder.build().now());
    }

    private void mockRedisStoreServiceForSpotifyCredentials() {

        when(credentialsRepository.save(SpotifyAuthCredentialsBuilder.build().now()))
                .thenReturn(SpotifyAuthCredentialsBuilder.build().now());
    }

}
