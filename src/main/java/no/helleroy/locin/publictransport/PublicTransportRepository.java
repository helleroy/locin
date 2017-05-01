package no.helleroy.locin.publictransport;

import java.util.List;

public interface PublicTransportRepository {

    List<Stop> getStopsWithinAreaUTM32(int xMin, int xMax, int yMin, int yMax);

    List<Visit> getVisitsForStop(Stop stop);
}
