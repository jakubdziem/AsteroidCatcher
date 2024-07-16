package com.dziem.asteroidcatcher;

import com.dziem.asteroidcatcher.service.SmallBodyDatabaseServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AsteroidCatcherApplication {

    public static void main(String[] args) {
//        SmallBodyDatabaseServiceImpl smallBodyDatabaseService = new SmallBodyDatabaseServiceImpl(new RestTemplate());
//        smallBodyDatabaseService.isVisibleInGivenCoordinates("2337252");
        SpringApplication.run(AsteroidCatcherApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
