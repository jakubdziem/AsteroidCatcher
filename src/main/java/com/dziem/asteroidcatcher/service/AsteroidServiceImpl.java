package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.nasaasteroids.Asteroid;
import com.dziem.asteroidcatcher.model.nasaasteroids.NasaApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AsteroidServiceImpl implements AsteroidService {

    private final RestTemplate restTemplate;
    @Override
    public List<Asteroid> getAsteroidsToday() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Result result = getResult(localDateTime);
        return result.response().getNear_earth_objects().get(result.startDate());
    }

    @Override
    public List<Asteroid> getAsteroids(String day) {
        //parser
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.parse(day), LocalTime.NOON);
        Result result = getResult(localDateTime);
        return result.response().getNear_earth_objects().get(result.startDate());
    }

    private Result getResult(LocalDateTime localDateTime) {
        String startDate = localDateTime.toString();
        int index = startDate.indexOf('T');
        startDate = startDate.substring(0,index);
        String endDate = localDateTime.plusDays(1).toString();
        endDate = endDate.substring(0,index);
        String apiKey = System.getenv("API_KEY");
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+ startDate +"&end_date="+ endDate +"&api_key="+apiKey;
        NasaApiResponse response = restTemplate.getForObject(url, NasaApiResponse.class);
        return new Result(startDate, response);
    }

    private record Result(String startDate, NasaApiResponse response) {
    }
}
