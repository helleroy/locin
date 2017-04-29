package no.helleroy.locin.citybike;

import java.util.List;

public interface CityBikeRepository {

    List<CityBikeStation> getClosestStations(double latitude, double longitude, int numberOfStations);
}
