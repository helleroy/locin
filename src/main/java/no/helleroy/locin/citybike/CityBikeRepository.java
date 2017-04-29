package no.helleroy.locin.citybike;

import java.util.List;

public interface CityBikeRepository {

    List<CityBikeStation> getClosestStations(Coordinates coordinates, int numberOfStations);
}
