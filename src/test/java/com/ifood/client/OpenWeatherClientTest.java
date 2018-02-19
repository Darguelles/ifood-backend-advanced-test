package com.ifood.client;

import com.ifood.model.WeatherResponse;
import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static feign.Util.toByteArray;

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
                    .decoder(new GsonDecoder())
                    .client(mockClient
                            .ok(HttpMethod.GET, "/data/2.5/weather?q=lima&APPID=12345&units=metric", data))
                    .target(new MockTarget<>(OpenWeatherClient.class));
        }
    }

    @Test
    public void shouldReturnOkForGetWeatherByCityName() {
        WeatherResponse weather = weatherClient.getCurrentWeatherByCityName("lima", "12345", "metric");
        Assert.assertThat(weather, CoreMatchers.notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldReturn404IfCityNameDoesNotMatch() {
        try {
            weatherClient.getCurrentWeatherByCityName("london", "12345", "metric");
            Assert.fail();
        } catch (FeignException e) {
            Assert.assertThat(e.getMessage(), Matchers.containsString("404"));
        }
    }

    @Test
    public void shouldMapCorrectlyTempValueReturnedByClient() {
        WeatherResponse weather = weatherClient.getCurrentWeatherByCityName("lima", "12345", "metric");
        System.out.println(weather.getMain().getTemp());
        Assert.assertThat(weather.getMain().getTemp(), CoreMatchers.notNullValue());
        mockClient.verifyStatus();
    }

    @Test
    public void shouldReturnCorrectTempValueHardcodedInJsonFile() {
        WeatherResponse weather = weatherClient.getCurrentWeatherByCityName("lima", "12345", "metric");
        System.out.println(weather.getMain().getTemp());
        Assert.assertThat(weather.getMain().getTemp(), CoreMatchers.is(DEFAULT_TEMP_VALUE));
        mockClient.verifyStatus();
    }

}
