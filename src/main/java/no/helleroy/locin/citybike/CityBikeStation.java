package no.helleroy.locin.citybike;

public class CityBikeStation {

    private final String name;
    private final double latitude;
    private final double longitude;
    private final int availableBikes;
    private final int availableLocks;
    private final boolean closed;

    public CityBikeStation(String name, double latitude, double longitude, int availableBikes, int availableLocks, boolean closed) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.availableBikes = availableBikes;
        this.availableLocks = availableLocks;
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "CityBikeStation{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", availableBikes=" + availableBikes +
                ", availableLocks=" + availableLocks +
                ", closed=" + closed +
                '}';
    }
}
