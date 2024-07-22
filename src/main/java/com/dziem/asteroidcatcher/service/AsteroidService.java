package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.nasaasteroids.Asteroid;

import java.util.List;

public interface AsteroidService {
    List<Asteroid> getAsteroidsToday();
    List<Asteroid> getAsteroids(String day);
}
