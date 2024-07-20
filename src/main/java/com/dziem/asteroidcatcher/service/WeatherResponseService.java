package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.model.weather.Current;
import com.dziem.asteroidcatcher.model.weather.Weather;


public interface WeatherResponseService {
    public int getCloudCover(Coordinates coordinates);
    public Current getCurrentFromApi(Coordinates coordinates);
    public Weather getWeather(Coordinates coordinates);
}
