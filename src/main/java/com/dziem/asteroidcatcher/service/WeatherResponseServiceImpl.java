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
        String url = "https://api.open-meteo.com/v1/forecast?latitude="+coordinates.getLatitude()+"&longitude="+coordinates.getLongitude()+"&current=temperature_2m,relative_humidity_2m,is_day,precipitation&daily=sunrise,sunset";
        WeatherResponseExpanded response = restTemplate.getForObject(url, WeatherResponseExpanded.class);
        return Weather.builder()
                .elevation(response.getElevation())
                .time(response.getCurrent().getTime())
                .interval(response.getCurrent().getInterval())
                .temperature(response.getCurrent().getTemperature_2m())
                .humidity(response.getCurrent().getRelative_humidity_2m())
                .precipitation(response.getCurrent().getPrecipitation())
                .sunrise(response.getDaily().getSunrise()[0])
                .sunset(response.getDaily().getSunset()[0])
                .build();
    }

}
