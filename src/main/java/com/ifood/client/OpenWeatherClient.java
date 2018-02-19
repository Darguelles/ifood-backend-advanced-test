package com.ifood.client;

import com.ifood.model.Weather;
import feign.Param;
import feign.RequestLine;

public interface OpenWeatherClient {


    @RequestLine("GET /data/2.5/weather?lat={lat}&lon={lon}&APPID={appId}&units={units}")
    Weather getCurrentWeatherByLocation(@Param("lat") Long latitude, @Param("lon") Long longitude,
                                        @Param("appId") String appId, @Param("units") String units);

    @RequestLine("GET /data/2.5/weather?q={cityName}&APPID={appId}&units={units}")
    Weather getCurrentWeatherByCityName(@Param("cityName") String cityName, @Param("appId") String appId,
                                        @Param("units") String units);

}
