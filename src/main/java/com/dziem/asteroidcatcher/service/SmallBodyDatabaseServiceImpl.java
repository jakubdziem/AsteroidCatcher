package com.dziem.asteroidcatcher.service;

import com.dziem.asteroidcatcher.model.Coordinates;
import com.dziem.asteroidcatcher.model.smallbodydb.Elements;
import com.dziem.asteroidcatcher.model.smallbodydb.SmallBodyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SmallBodyDatabaseServiceImpl implements SmallBodyDatabaseService {
    private final RestTemplate restTemplate;

    @Override
    public boolean isVisibleInGivenCoordinates(String id, Coordinates coordinates) {
        double latitude = Double.parseDouble(coordinates.getLatitude());
        double longitude = Double.parseDouble(coordinates.getLongitude());

        String url = "https://ssd-api.jpl.nasa.gov/sbdb.api?spk="+id;
        SmallBodyResponse response = restTemplate.getForObject(url, SmallBodyResponse.class);
        Elements[] elements = response.getOrbit().getElements();
        String epoch = response.getOrbit().getEpoch();
        double eccentricity = Double.parseDouble(elements[0].getValue());
        double semiMajorAxis = Double.parseDouble(elements[1].getValue());
        double meanAnomaly = Double.parseDouble(elements[6].getValue()); // Mean anomaly in degrees

        double[] equatorialCoords = calculateEquatorialCoordinates(eccentricity, semiMajorAxis, meanAnomaly, epoch);
        double[] horizontalCoords = convertEquatorialToHorizontal(latitude, longitude, LocalDateTime.now(), equatorialCoords[0], equatorialCoords[1]);

        double altitude = horizontalCoords[0];
        double azimuth = horizontalCoords[1];

        return altitude > 0; // Visible if altitude > 0
    }

    // Convert equatorial coordinates (RA, Dec) to horizontal coordinates (Alt, Az)
    private double[] convertEquatorialToHorizontal(double latitude, double longitude, LocalDateTime observationTime, double ra, double dec) {
        // Convert observer's latitude and longitude to radians
        double observerLatRad = Math.toRadians(latitude);
        double observerLonRad = Math.toRadians(longitude);

        // Calculate Greenwich Sidereal Time (GST) in radians
        double gst = calculateGST(observationTime);
        double lst = gst + observerLonRad; // Local Sidereal Time (LST) in radians

        // Convert RA and Dec to radians
        double raRad = Math.toRadians(ra);
        double decRad = Math.toRadians(dec);

        // Calculate hour angle (HA) in radians
        double ha = lst - raRad;

        // Calculate altitude in radians
        double altitudeRad = Math.asin(Math.sin(decRad) * Math.sin(observerLatRad) + Math.cos(decRad) * Math.cos(observerLatRad) * Math.cos(ha));

        // Calculate azimuth in radians
        double azimuthRad = Math.atan2(-Math.sin(ha), Math.cos(decRad) * Math.tan(observerLatRad) - Math.sin(decRad) * Math.cos(ha));

        // Convert altitude and azimuth to degrees and normalize azimuth to [0, 360) degrees
        double altitudeDeg = Math.toDegrees(altitudeRad);
        double azimuthDeg = Math.toDegrees(azimuthRad);
        azimuthDeg = (azimuthDeg + 360) % 360;

        return new double[]{altitudeDeg, azimuthDeg};
    }

    // Calculate Greenwich Sidereal Time (GST) in radians
    private double calculateGST(LocalDateTime observationTime) {
        // Example placeholder implementation using UTC time
        // For accurate calculation, consider using precise formulas for GST
        double utcSeconds = observationTime.toEpochSecond(java.time.ZoneOffset.UTC);
        double jd = 2440587.5 + (utcSeconds / 86400.0);
        double t = (jd - 2451545.0) / 36525.0;
        double gst = 280.46061837 + 360.98564736629 * (jd - 2451545.0) + 0.000387933 * t * t - (t * t * t / 38710000.0);
        gst %= 360;
        if (gst < 0) {
            gst += 360;
        }
        return Math.toRadians(gst);
    }

    // Calculate equatorial coordinates (RA, Dec) based on orbital elements
    private double[] calculateEquatorialCoordinates(double eccentricity, double semiMajorAxis, double meanAnomaly, String epoch) {
        // Example placeholder implementation:
        double ra = 250.0; // Example RA in degrees
        double dec = 10.0; // Example Dec in degrees
        return new double[]{ra, dec};
    }
}
