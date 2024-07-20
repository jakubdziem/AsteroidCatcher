package com.dziem.asteroidcatcher.model.weather;

import lombok.Data;

@Data
public class Daily {
    private String[] time;
    private String[] sunrise;
    private String[] sunset;
}
