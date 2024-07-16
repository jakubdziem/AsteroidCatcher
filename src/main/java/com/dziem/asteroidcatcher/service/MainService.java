package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.nasaasteroids.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;

import java.util.List;
import java.util.Map;

public interface MainService {
    Map<String, List<Asteroid>> getVisibleAsteroidsForCity(String city);
    Map<String, List<Asteroid>> getVisibleAsteroidsForCoordinates(Coordinates coordinates);


}
