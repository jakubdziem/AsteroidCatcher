package com.dziem.asteroidcatcher.model.nasaasteroids;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class NasaApiResponse {
    private Links links;
    private int element_count;
    private Map<String, List<Asteroid>> near_earth_objects;
}
