package no.helleroy.locin.citybike;

public class CityBikeStation {

    private final String name;
    private final Coordinates coordinates;
    private final int availableBikes;
    private final int availableLocks;
    private final boolean closed;

    public CityBikeStation(String name, Coordinates coordinates, int availableBikes, int availableLocks, boolean closed) {
        this.name = name;
        this.coordinates = coordinates;
        this.availableBikes = availableBikes;
        this.availableLocks = availableLocks;
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public int getAvailableLocks() {
        return availableLocks;
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    public String toString() {
        return "CityBikeStation{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", availableBikes=" + availableBikes +
                ", availableLocks=" + availableLocks +
                ", closed=" + closed +
                '}';
    }
}
