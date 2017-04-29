package no.helleroy.locin.citybike;

import static java.lang.Math.abs;
import static java.lang.Math.hypot;

public class Coordinates {

    private final double latitude;
    private final double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distance(Coordinates other) {
        return hypot(abs(this.latitude - other.latitude), abs(this.longitude - other.longitude));
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
