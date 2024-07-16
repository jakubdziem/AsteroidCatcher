package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;

public interface SmallBodyDatabaseService {
    boolean isVisibleInGivenCoordinates(String id, Coordinates coordinates);
}
