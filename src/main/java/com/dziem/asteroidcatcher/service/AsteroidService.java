package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;

import java.util.List;

public interface AsteroidService {
    List<Asteroid> getAsteroids();
}
