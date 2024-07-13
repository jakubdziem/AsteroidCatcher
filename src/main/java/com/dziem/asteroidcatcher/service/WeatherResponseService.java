package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;


public interface WeatherResponseService {
    public int getCloudCover(Coordinates coordinates);

}
