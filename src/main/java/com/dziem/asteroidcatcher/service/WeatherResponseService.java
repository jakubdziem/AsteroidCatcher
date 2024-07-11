package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.controller.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public interface WeatherResponseService {
    public int getCloudCover(Coordinates coordinates);

}
