package com.dziem.asteroidcatcher.controller;

import com.dziem.asteroidcatcher.model.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.service.AsteroidService;
import com.dziem.asteroidcatcher.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AsteroidController {
    public final static String ASTEROIDS_PATH = "/asteroids";
    public final static String VISIBILITY_PATH_CITY = "/visibility/{city}";
    public final static String VISIBILITY_PATH= "/visibility";
    private final AsteroidService asteroidService;
    private final MainService mainService;

    @GetMapping(ASTEROIDS_PATH)
    public List<Asteroid> getAsteroids() {
        return asteroidService.getAsteroids();
    }

    @GetMapping(VISIBILITY_PATH_CITY)
    public List<Asteroid> getVisibilityForCity(@PathVariable("city") String city) {
        return mainService.getVisibleAsteroidsForCity(city);
    }
    @GetMapping(VISIBILITY_PATH)
    public List<Asteroid> getVisibilityExample() {
        return mainService.getVisibleAsteroidsForCoordinates(new Coordinates("41.71","67.31"));
    }
}
