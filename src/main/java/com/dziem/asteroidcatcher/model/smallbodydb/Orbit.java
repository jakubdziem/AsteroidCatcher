package com.dziem.asteroidcatcher.model.smallbodydb;

import lombok.Data;

@Data
public class Orbit {
    private Elements[] elements;
    private String epoch;
}
