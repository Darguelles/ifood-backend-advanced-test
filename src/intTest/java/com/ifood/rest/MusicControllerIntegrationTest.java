package com.ifood.rest;


import com.ifood.IfoodMusicApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IfoodMusicApplication.class, webEnvironment = RANDOM_PORT)
public class MusicControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnTrackListByLocationName() throws Exception {
        given()
                .queryParam("location", "lima")
                .when()
                .get("/playlist")
                .then()
                .body(containsString("currentTemperature"));
    }
}
