package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.model.weather.Current;
import com.dziem.asteroidcatcher.model.weather.Weather;
import com.dziem.asteroidcatcher.model.weather.WeatherResponse;
import com.dziem.asteroidcatcher.model.weather.WeatherResponseExpanded;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class WeatherResponseServiceImpl implements WeatherResponseService {
    private final RestTemplate restTemplate;
    @Override
    public int getCloudCover(Coordinates coordinates) {
        Current current = getCurrentFromApi(coordinates);
        return current.getCloud_cover();
    }

    @Override
    public Current getCurrentFromApi(Coordinates coordinates) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude="+coordinates.getLatitude()+"&longitude="+coordinates.getLongitude()+"&current=cloud_cover";
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response != null ? response.getCurrent() : new Current();
    }

    @Override
    public Weather getWeather(Coordinates coordinates) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude="+coordinates.getLatitude()+"&longitude="+coordinates.getLongitude()+"&current=temperature_2m,relative_humidity_2m,is_day,precipitation,cloud_cover&daily=sunrise,sunset&timezone=auto";
        WeatherResponseExpanded response = restTemplate.getForObject(url, WeatherResponseExpanded.class);


        String sunrise = response.getDaily().getSunrise()[0];
        sunrise = sunrise.substring(sunrise.indexOf('T')+1);
        String sunset = response.getDaily().getSunset()[0];
        sunset = sunset.substring(sunset.indexOf('T')+1);
        return Weather.builder()
                .time(response.getCurrent().getTime())
                .interval(response.getCurrent().getInterval())
                .elevation(response.getElevation())
                .temperature(response.getCurrent().getTemperature_2m())
                .humidity(response.getCurrent().getRelative_humidity_2m())
                .precipitation(response.getCurrent().getPrecipitation())
                .cloudCover(response.getCurrent().getCloud_cover())
                .sunrise(sunrise)
                .sunset(sunset)
                .build();
    }
}
