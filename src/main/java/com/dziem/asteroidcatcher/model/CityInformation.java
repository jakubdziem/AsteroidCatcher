package com.dziem.asteroidcatcher.model;

import lombok.Data;

@Data
public class CityInformation {
    private String name;
    private double latitude;
    private double longitude;
    private String country;
}
