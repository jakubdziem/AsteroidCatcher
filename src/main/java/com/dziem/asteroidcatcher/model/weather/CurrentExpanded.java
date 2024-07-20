package com.dziem.asteroidcatcher.model.weather;

import lombok.Data;

@Data
public class CurrentExpanded {
    private String time;
    private String interval;
    private String temperature_2m;
    private String relative_humidity_2m;
    private String precipitation;
}
