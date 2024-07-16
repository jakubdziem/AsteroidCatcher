package com.dziem.asteroidcatcher.controller;

import com.dziem.asteroidcatcher.model.nasaasteroids.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.service.AsteroidService;
import com.dziem.asteroidcatcher.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AsteroidController {
    public final static String ASTEROIDS_PATH = "/asteroids";
    public final static String VISIBILITY_PATH_CITY = "/visibility/{city}";
    public final static String VISIBILITY_PATH_COORDINATES = "/visibility/{latitude}/{longitude}";
    public final static String VISIBILITY_PATH = "/visibility";
    private final AsteroidService asteroidService;
    private final MainService mainService;
    private final static double limitingMagnitudeNakedEye = 6.5d;

    @GetMapping(ASTEROIDS_PATH)
    public List<Asteroid> getAsteroids() {
        return asteroidService.getAsteroids();
    }

    @GetMapping(VISIBILITY_PATH_CITY)
    public Map<String, List<Asteroid>> getVisibilityForCityNakedEye(@PathVariable("city") String city) {
        return mainService.getVisibleAsteroidsForCity(city, limitingMagnitudeNakedEye);
    }
    @GetMapping(VISIBILITY_PATH)
    public Map<String, List<Asteroid>> getVisibilityExampleNakedEye() {
        return mainService.getVisibleAsteroidsForCoordinates(new Coordinates("41.71","67.31"), limitingMagnitudeNakedEye);
    }
    @GetMapping(VISIBILITY_PATH_COORDINATES)
    public Map<String, List<Asteroid>> getVisibilityForCoordinatesNakedEye(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude) {
        return mainService.getVisibleAsteroidsForCoordinates(new Coordinates(latitude,longitude), limitingMagnitudeNakedEye);
    }
}
