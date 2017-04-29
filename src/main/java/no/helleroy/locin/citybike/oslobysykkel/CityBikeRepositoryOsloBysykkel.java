package no.helleroy.locin.citybike.oslobysykkel;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.citybike.CityBikeRepository;
import no.helleroy.locin.citybike.CityBikeStation;
import no.helleroy.locin.citybike.Coordinates;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class CityBikeRepositoryOsloBysykkel implements CityBikeRepository {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper jsonMapper;
    private final HttpUrl url;
    private final String clientIdentifier;

    public CityBikeRepositoryOsloBysykkel(OkHttpClient okHttpClient, ObjectMapper jsonMapper, HttpUrl url, String clientIdentifier) {
        this.okHttpClient = okHttpClient;
        this.jsonMapper = jsonMapper;
        this.url = url;
        this.clientIdentifier = clientIdentifier;
    }

    @Override
    public List<CityBikeStation> getClosestStations(Coordinates coordinates, int numberOfStations) {

        StationsResponse stations = getStations();
        StationsResponse availability = getAvailability();
        StatusResponse status = getStatus();

        return Stream.concat(stations.getStations().stream(), availability.getStations().stream())
                .collect(groupingBy(Station::getId))
                .values()
                .stream()
                .filter(stationsWithId -> stationsWithId.size() > 1)
                .map(stationsWithId -> stationsWithId
                        .stream()
                        .reduce((left, right) -> {
                            Station station = new Station();
                            station.setId(left.getId());
                            station.setTitle(left.getTitle() != null ? left.getTitle() : right.getTitle());
                            station.setSubtitle(left.getSubtitle() != null ? left.getSubtitle() : right.getSubtitle());
                            station.setCenter(left.getCenter() != null ? left.getCenter() : right.getCenter());
                            station.setAvailability(left.getAvailability() != null ? left.getAvailability() : right.getAvailability());
                            return station;
                        })
                        .orElseThrow(() -> new RuntimeException("Something went terribly wrong when combining station and availability data for Oslo Bysykkel"))
                )
                .map(station -> {
                    boolean closed = false;
                    if (status.getStatus().isAllStationsClosed() || status.getStatus().getStationsClosed().contains(station.getId())) {
                        closed = true;
                    }
                    return new CityBikeStation(
                            station.getTitle() + " " + station.getSubtitle(),
                            new Coordinates(
                                    station.getCenter().getLatitude(),
                                    station.getCenter().getLongitude()),
                            station.getAvailability().getBikes(),
                            station.getAvailability().getLocks(),
                            closed);
                })
                .sorted(comparing(cityBikeStation -> cityBikeStation.getCoordinates().distance(coordinates)))
                .limit(numberOfStations)
                .collect(toList());
    }

    private StationsResponse getStations() {
        return getFromAPI(url.newBuilder()
                        .addPathSegment("api")
                        .addPathSegment("v1")
                        .addPathSegment("stations")
                        .build(),
                StationsResponse.class);
    }

    private StationsResponse getAvailability() {
        return getFromAPI(url.newBuilder()
                        .addPathSegment("api")
                        .addPathSegment("v1")
                        .addPathSegment("stations")
                        .addPathSegment("availability")
                        .build(),
                StationsResponse.class);
    }

    private StatusResponse getStatus() {
        return getFromAPI(url.newBuilder()
                        .addPathSegment("api")
                        .addPathSegment("v1")
                        .addPathSegment("status")
                        .build(),
                StatusResponse.class);
    }

    private <T> T getFromAPI(HttpUrl url, Class<T> parseType) {
        try {
            Response availabilityResponse = okHttpClient.newCall(
                    new Request.Builder()
                            .header("Client-Identifier", clientIdentifier)
                            .url(url)
                            .build())
                    .execute();

            return jsonMapper.readValue(availabilityResponse.body().byteStream(), parseType);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong when calling Oslo Bysykkel API", e);
        }
    }
}
