package com.dziem.asteroidcatcher.controller;

import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.model.nasaasteroids.Asteroid;
import com.dziem.asteroidcatcher.model.weather.Current;
import com.dziem.asteroidcatcher.model.weather.Weather;
import com.dziem.asteroidcatcher.service.AsteroidService;
import com.dziem.asteroidcatcher.service.GeocodingService;
import com.dziem.asteroidcatcher.service.MainService;
import com.dziem.asteroidcatcher.service.WeatherResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PagesController {
    private final AsteroidService asteroidService;
    private final MainService mainService;
    private final WeatherResponseService weatherResponseService;
    private final GeocodingService geocodingService;
    public final String VISIBLE_ASTEROIDS_PATH = "/visibleasteroids";
    @GetMapping("/asteroidcatcher")
    public String getMainPage() {
        return "asteroidcatcher";
    }
    @GetMapping("/asteroidslist")
    public String getAsteroidPage() {
        return "asteroidslist";
    }
    @ModelAttribute("asteroids")
    public List<Asteroid> getAsteroids() {
        return asteroidService.getAsteroids();
    }
    @GetMapping("/qa")
    public String getQaPage() {
        return "qa";
    }
    @GetMapping(VISIBLE_ASTEROIDS_PATH)
    public String getVisibleAsteroidsPage() {
        return "visibleasteroids";
    }

    @ModelAttribute("weatherCity")
    @RequestMapping(VISIBLE_ASTEROIDS_PATH)
    public Weather getWeatherForCity(@RequestParam("city") Optional<String> opCity) {
        String city = opCity.get().equals("") ? "London" : opCity.orElse("London");
        Coordinates coordinatesOfCity = geocodingService.getCoordinatesOfCity(city);
        return weatherResponseService.getWeather(coordinatesOfCity);
    }

    @PostMapping(value = VISIBLE_ASTEROIDS_PATH, params = "action=city")
    public String getVisibilityForCity(@RequestParam("city") String city, Model model) {
        Weather weather = getWeatherForCity(Optional.of(city));
        Map<String, List<Asteroid>> visibleAsteroids = mainService.getVisibleAsteroidsForCity(city);
        model.addAttribute("weatherCity", weather);
        model.addAttribute("visibleAsteroids", visibleAsteroids);
        model.addAttribute("city", city);
        return "visibleasteroids";
    }

    @PostMapping(value = VISIBLE_ASTEROIDS_PATH, params = "action=coordinates")
    public String getVisibilityForCoordinates(@RequestParam("latitude") String latitude,
                                              @RequestParam("longitude") String longitude, Model model) {
        Coordinates coordinates = new Coordinates(latitude, longitude);
        Weather weather = weatherResponseService.getWeather(coordinates);
        Map<String, List<Asteroid>> visibleAsteroids = mainService.getVisibleAsteroidsForCoordinates(coordinates);
        model.addAttribute("weatherCity", weather);
        model.addAttribute("visibleAsteroids", visibleAsteroids);
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        return "visibleasteroids";
    }
}
