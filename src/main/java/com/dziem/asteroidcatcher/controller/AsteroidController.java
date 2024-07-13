package com.dziem.asteroidcatcher.controller;

import com.dziem.asteroidcatcher.model.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.service.AsteroidService;
import com.dziem.asteroidcatcher.service.WeatherResponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class AsteroidController {
    private final AsteroidService asteroidService;
    private final WeatherResponseService weatherResponseService;

    @GetMapping("/asteroids")
    public List<Asteroid> getAsteroids() {
        return asteroidService.getAsteroids();
    }

    @GetMapping("/visibility")
//    @RequestParam("location") String location
    public List<Asteroid> getVisibility() {
        Coordinates coordinates = new Coordinates("41.40","2.17");
        int cloudCover = weatherResponseService.getCloudCover(coordinates);
        List<Asteroid> asteroids = asteroidService.getAsteroids();
        List<Asteroid> visibleAsteroids = new ArrayList<>();
        if(cloudCover > 50) {
            return new ArrayList<>();
        }
        for (Asteroid asteroid : asteroids) {
            if(asteroid.getAbsolute_magnitude_h() > 6.5d
            && asteroid.getEstimated_diameter().getKilometers().getEstimated_diameter_min() > 0.2d) {
                visibleAsteroids.add(asteroid);
            } //magnitude defines the brightness of the asteroid
            //diameter is to check if the asteroid is too small to observe 1 is just an example
        }

        return visibleAsteroids;
    }
}
