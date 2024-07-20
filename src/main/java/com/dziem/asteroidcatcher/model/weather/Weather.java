package com.dziem.asteroidcatcher.model.weather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {
    private String elevation;
    private String time;
    private String interval;
    private String temperature;
    private String humidity;
    private String precipitation;
    private String sunrise;
    private String sunset;
}
