package com.ifood.client;

import com.ifood.config.FeignClientConfig;
import com.ifood.config.HystrixOpenWeatherFallbackConfig;
import com.ifood.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "openWeatherClient",url = "${openweather.host}", fallbackFactory = HystrixOpenWeatherFallbackConfig.class, configuration = FeignClientConfig.class)
public interface OpenWeatherClient {

    @RequestMapping(method = GET, value = "/data/2.5/weather?lat={lat}&lon={lon}&APPID={appId}&units={units}")
    WeatherResponse getCurrentWeatherByLocation(@PathVariable("lat") Double latitude, @PathVariable("lon") Double longitude,
                                                @PathVariable("appId") String appId, @PathVariable("units") String units);

    @RequestMapping(method = GET, value = "/data/2.5/weather?q={cityName}&APPID={appId}&units={units}")
    WeatherResponse getCurrentWeatherByCityName(@PathVariable("cityName") String cityName, @PathVariable("appId") String appId,
                                                @PathVariable("units") String units);

}
