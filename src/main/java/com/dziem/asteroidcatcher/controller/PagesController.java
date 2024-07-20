package com.dziem.asteroidcatcher.controller;

import com.dziem.asteroidcatcher.model.nasaasteroids.Asteroid;
import com.dziem.asteroidcatcher.service.AsteroidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PagesController {
    private final AsteroidService asteroidService;
    @GetMapping("/asteroidcatcher")
    public String getMainPage() {
        return "asteroidcatcher";
    }
    @GetMapping("asteroidslist")
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
    @GetMapping("/visibleasteroids")
    public String getVisbileAsteroidsPage() {
        return "visibleasteroids";
    }
}
