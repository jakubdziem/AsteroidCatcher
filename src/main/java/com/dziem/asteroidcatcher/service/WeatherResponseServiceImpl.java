package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.model.weather.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherResponseServiceImpl implements WeatherResponseService {
    private final RestTemplate restTemplate;
    @Override
    public int getCloudCover(Coordinates coordinates) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude="+coordinates.getLatitude()+"&longitude="+coordinates.getLongitude()+"&current=cloud_cover";
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        return response.getCurrent().getCloud_cover();
    }
}
