package com.dziem.asteroidcatcher.model;

import lombok.Data;

import java.util.List;
@Data
public class GeocodingResponse {
    private List<CityInformation> cityInformationList;
}
