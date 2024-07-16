package com.dziem.asteroidcatcher.model.geocoding;

import lombok.Data;

import java.util.List;
@Data
public class GeocodingResponse {
    private List<CityInformation> cityInformationList;
}
