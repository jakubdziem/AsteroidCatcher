package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    private final WeatherResponseService weatherResponseService;
    private final GeocodingService geocodingService;
    private final AsteroidService asteroidService;
    @Override
    public List<Asteroid> getVisibleAsteroidsForCity(String city) {
        Coordinates coordinates = geocodingService.getCoordinatesOfCity(city);
        List<Asteroid> asteroids = asteroidService.getAsteroids();
        int cloudCover = weatherResponseService.getCloudCover(coordinates);
        return getVisibleAsteroids(cloudCover, asteroids);
    }
    @Override
    public List<Asteroid> getVisibleAsteroidsForCoordinates(Coordinates coordinates) {
        List<Asteroid> asteroids = asteroidService.getAsteroids();
        int cloudCover = weatherResponseService.getCloudCover(coordinates);
        return getVisibleAsteroids(cloudCover,asteroids);
    }
    private static List<Asteroid> getVisibleAsteroids(int cloudCover, List<Asteroid> asteroids) {
        List<Asteroid> visibleAsteroids = new ArrayList<>();
        if(cloudCover > 80) {
            return new ArrayList<>();
        }
        for (Asteroid asteroid: asteroids) {
            if(asteroid.getAbsolute_magnitude_h() > 6.5d
                    && asteroid.getEstimated_diameter().getKilometers().getEstimated_diameter_min() > 0.2d) {
                visibleAsteroids.add(asteroid);
            } //magnitude defines the brightness of the asteroid
            //diameter is to check if the asteroid is too small to observe 1 is just an example
        }
        return visibleAsteroids;
    }
}
