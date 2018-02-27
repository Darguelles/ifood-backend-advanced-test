package com.ifood.rest;


import com.ifood.IfoodMusicApplicationIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IfoodMusicApplicationIntegrationTest.class)
public class MusicControllerIntegrationTest {

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
