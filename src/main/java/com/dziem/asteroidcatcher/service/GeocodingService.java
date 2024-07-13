package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;

public interface GeocodingService {
    Coordinates getCoordinatesOfCity(String city);
}
