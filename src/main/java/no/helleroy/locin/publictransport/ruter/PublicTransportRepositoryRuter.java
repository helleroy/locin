package no.helleroy.locin.publictransport.ruter;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.publictransport.Line;
import no.helleroy.locin.publictransport.PublicTransportRepository;
import no.helleroy.locin.publictransport.Stop;
import no.helleroy.locin.publictransport.Visit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class PublicTransportRepositoryRuter implements PublicTransportRepository {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper jsonMapper;
    private final HttpUrl url;

    public PublicTransportRepositoryRuter(OkHttpClient okHttpClient, ObjectMapper jsonMapper, HttpUrl url) {
        this.okHttpClient = okHttpClient;
        this.jsonMapper = jsonMapper;
        this.url = url;
    }

    @Override
    public List<Stop> getStopsWithinAreaUTM32(int xMin, int xMax, int yMin, int yMax) {
        try {
            Response response = okHttpClient.newCall(
                    new Request.Builder()
                            .url(url.newBuilder()
                                    .addPathSegment("Place")
                                    .addPathSegment("GetStopsByArea")
                                    .addQueryParameter("xmin", Integer.toString(xMin))
                                    .addQueryParameter("xmax", Integer.toString(xMax))
                                    .addQueryParameter("ymin", Integer.toString(yMin))
                                    .addQueryParameter("ymax", Integer.toString(yMax))
                                    .build())
                            .build())
                    .execute();

            List<RuterStop> ruterStops = asList(jsonMapper.readValue(response.body().byteStream(), RuterStop[].class));

            return ruterStops.stream()
                    .map(ruterStop -> new Stop(ruterStop.getId(), ruterStop.getName()))
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong when calling Ruter API", e);
        }
    }

    @Override
    public List<Visit> getVisitsForStop(Stop stop) {
        try {
            Response response = okHttpClient.newCall(
                    new Request.Builder()
                            .url(url.newBuilder()
                                    .addPathSegment("StopVisit")
                                    .addPathSegment("GetDepartures")
                                    .addPathSegment(stop.getId())
                                    .build())
                            .build())
                    .execute();

            List<RuterVisit> ruterVisits = asList(jsonMapper.readValue(response.body().byteStream(), RuterVisit[].class));

            return ruterVisits.stream()
                    .map(ruterVisit -> new Visit(
                            new Line(
                                    ruterVisit.getMonitoredVehicleJourney().getPublishedLineName(),
                                    ruterVisit.getMonitoredVehicleJourney().getDestinationName(),
                                    new VehicleMode(ruterVisit.getMonitoredVehicleJourney().getVehicleMode()).toTransportType().orElse(null),
                                    ruterVisit.getExtensions().getDeviations().stream().map(RuterVisit.Extensions.Deviation::getHeader).collect(toList())),
                            stop,
                            ruterVisit.getMonitoredVehicleJourney().getMonitoredCall().getExpectedDepartureTime()))
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong when calling Ruter API", e);
        }
    }
}
