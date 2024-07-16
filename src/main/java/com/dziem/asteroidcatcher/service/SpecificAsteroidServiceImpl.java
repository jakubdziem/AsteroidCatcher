package com.dziem.asteroidcatcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//TO DO
@Service
@RequiredArgsConstructor
public class SpecificAsteroidServiceImpl implements SpecificAsteroidService {
    private final RestTemplate restTemplate;

    @Override
    public boolean isVisibleInGivenCoordinates(String id) {
        String apiKey = System.getenv("API_KEY");
        String url = "https://api.nasa.gov/neo/rest/v1/neo/"+id+"?api_key="+apiKey;
        return false;
    }
}
