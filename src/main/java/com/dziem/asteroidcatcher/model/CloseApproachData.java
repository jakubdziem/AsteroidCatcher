package com.dziem.asteroidcatcher.model;

import lombok.Data;

@Data
public class CloseApproachData {
    private String close_approach_date;
    private String close_approach_date_full;
    private long epoch_date_close_approach;
    private RelativeVelocity relative_velocity;
    private MissDistance miss_distance;
    private String orbiting_body;
}
