package com.dziem.asteroidcatcher.model;

import lombok.Data;

@Data
public class Current {
    private String time;
    private int interval;
    private int cloud_cover;
}
