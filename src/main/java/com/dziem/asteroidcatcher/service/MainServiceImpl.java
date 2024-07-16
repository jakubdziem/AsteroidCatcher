package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Asteroid;
import com.dziem.asteroidcatcher.model.Coordinates;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    private final WeatherResponseService weatherResponseService;
    private final GeocodingService geocodingService;
    private final AsteroidService asteroidService;
    @Override
    public Map<String, List<Asteroid>> getVisibleAsteroidsForCity(String city, double limitingMagnitude) {
        Coordinates coordinates = geocodingService.getCoordinatesOfCity(city);
        List<Asteroid> asteroids = asteroidService.getAsteroids();
        int cloudCover = weatherResponseService.getCloudCover(coordinates);
        return getVisibleAsteroids(cloudCover, asteroids,limitingMagnitude);
    }
    @Override
    public Map<String, List<Asteroid>> getVisibleAsteroidsForCoordinates(Coordinates coordinates, double limitingMagnitude) {
        List<Asteroid> asteroids = asteroidService.getAsteroids();
        int cloudCover = weatherResponseService.getCloudCover(coordinates);
        return getVisibleAsteroids(cloudCover,asteroids, limitingMagnitude);
    }
    private static Map<String,List<Asteroid>> getVisibleAsteroids(int cloudCover, List<Asteroid> asteroids, double limitingMagnitude) {
        Map<String,List<Asteroid>> visibleAsteroids = new HashMap<>();
        List<Asteroid> nakedEye = new ArrayList<>();
        List<Asteroid> smallTelescope = new ArrayList<>();
        List<Asteroid> mediumTelescope = new ArrayList<>();

        if(cloudCover > 80) {
            return visibleAsteroids;
        }
        for (Asteroid asteroid: asteroids) {
            double distanceAU = Double.parseDouble(asteroid.getClose_approach_data().get(0).getMiss_distance().getAstronomical());
            double apparentMagnitude = asteroid.getAbsolute_magnitude_h() + 5 * Math.log10(distanceAU) - 5;
            if(apparentMagnitude <= 6.5d) {
                nakedEye.add(asteroid);
            } //magnitude defines the brightness of the asteroid
            else if(apparentMagnitude <= 12d) {
                smallTelescope.add(asteroid);
            }
            else if(apparentMagnitude <= 14d) {
                mediumTelescope.add(asteroid);
            }
            /*
             * Apparent Magnitude Calculation
             * The apparent magnitude
             * m is what you would observe from Earth and is calculated using:
             * m=H+5log10(d)-5
             * where:
             * H is the absolute magnitude.
             * d is the distance from Earth to the asteroid in astronomical units (AU).
             */
        }
        visibleAsteroids.put("Naked Eye", nakedEye);
        visibleAsteroids.put("Small Telescope", smallTelescope);
        visibleAsteroids.put("Medium Telescope", mediumTelescope);
        return visibleAsteroids;
    }

}
