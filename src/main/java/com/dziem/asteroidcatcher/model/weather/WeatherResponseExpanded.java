package com.dziem.asteroidcatcher.model.weather;

import lombok.Data;

@Data
public class WeatherResponseExpanded {
    private String elevation;
    private CurrentExpanded current;
    private Daily daily;
}
