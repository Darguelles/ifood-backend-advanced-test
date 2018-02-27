package com.ifood.client;

import com.ifood.model.WeatherResponse;
import feign.Feign;
import feign.FeignException;
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
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class OpenWeatherClientTest {

    private MockClient mockClient;
    private OpenWeatherClient weatherClient;

    private final static Double DEFAULT_TEMP_VALUE = 14.54;


    @Before
    public void setUp() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("WeatherResponse.json")) {
            byte[] data = toByteArray(input);
            mockClient = new MockClient();
            weatherClient = Feign.builder()
                    .contract(new SpringMvcContract())
                    .decoder(new GsonDecoder())
                    .client(mockClient
                            .ok(GET, "/data/2.5/weather?q=lima&APPID=12345&units=metric", data))
                    .target(new MockTarget<>(OpenWeatherClient.class));
        }
    }

    @Test
    public void shouldReturnOkForGetWeatherByCityName() {
        WeatherResponse weather = weatherClient.getCurrentWeatherByCityName("lima", "12345", "metric");
        assertThat(weather, notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldReturn404IfCityNameDoesNotMatch() {
        try {
            weatherClient.getCurrentWeatherByCityName("london", "12345", "metric");
            fail();
        } catch (FeignException e) {
            assertThat(e.getMessage(), containsString("404"));
        }
    }

    @Test
    public void shouldMapCorrectlyTempValueReturnedByClient() {
        WeatherResponse weather = weatherClient.getCurrentWeatherByCityName("lima", "12345", "metric");
        assertThat(weather.getMain().getTemp(), notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldReturnCorrectTempValueHardcodedInJsonFile() {
        WeatherResponse weather = weatherClient.getCurrentWeatherByCityName("lima", "12345", "metric");
        assertThat(weather.getMain().getTemp(), is(DEFAULT_TEMP_VALUE));
        mockClient.verifyStatus();
    }

}
