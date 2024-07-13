package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.model.CityInformation;
import com.dziem.asteroidcatcher.model.GeocodingResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {
    private final RestTemplate restTemplate;

    @Override
    public Coordinates getCoordinatesOfCity(String city) {
        String url = "https://api.api-ninjas.com/v1/geocoding?city=London&country="+city;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", System.getenv("X-Api-Key"));

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<CityInformation[]> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CityInformation[].class);
        CityInformation[] response = exchange.getBody();

        CityInformation cityInformation = response[0];
        return new Coordinates(String.valueOf(cityInformation.getLongitude()), String.valueOf(cityInformation.getLatitude()));
    }
}
