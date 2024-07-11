package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Asteroid;
import com.dziem.asteroidcatcher.model.NasaApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class AsteroidServiceImpl implements AsteroidService {

    private final RestTemplate restTemplate;
    @Override
    public List<Asteroid> getAsteroids() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String startDate = localDateTime.toString();
        int index = startDate.indexOf('T');
        startDate = startDate.substring(0,index);
        String endDate = LocalDateTime.now().plusDays(1).toString();
        endDate = endDate.substring(0,index);
        String apiKey = System.getenv("API_KEY");
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+startDate+"&end_date="+endDate+"&api_key="+apiKey;
        NasaApiResponse response = restTemplate.getForObject(url, NasaApiResponse.class);
        return response.getNear_earth_objects().get(startDate);
    }
}
