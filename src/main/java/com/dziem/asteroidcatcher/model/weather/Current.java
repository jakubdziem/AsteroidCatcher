package com.dziem.asteroidcatcher.model.weather;

import lombok.Data;

@Data
public class Current {
    private String time;
    private int interval;
    private int cloud_cover;
}
