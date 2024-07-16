package com.dziem.asteroidcatcher.model.nasaasteroids;

import lombok.Data;

import java.util.List;

@Data
public class Asteroid {
    private String id;
    private String neo_reference_id;
    private String name;
    private String nasa_jpl_url;
    private double absolute_magnitude_h;
    private EstimatedDiameter estimated_diameter;
    private boolean is_potentially_hazardous_asteroid;
    private List<CloseApproachData> close_approach_data;
    private boolean is_sentry_object;

}
