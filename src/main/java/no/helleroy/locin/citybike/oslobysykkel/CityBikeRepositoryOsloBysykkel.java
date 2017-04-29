package no.helleroy.locin.citybike.oslobysykkel;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.citybike.CityBikeRepository;
import no.helleroy.locin.citybike.CityBikeStation;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

public class CityBikeRepositoryOsloBysykkel implements CityBikeRepository {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper jsonMapper;
    private final HttpUrl url;

    public CityBikeRepositoryOsloBysykkel(OkHttpClient okHttpClient, ObjectMapper jsonMapper, HttpUrl url) {
        this.okHttpClient = okHttpClient;
        this.jsonMapper = jsonMapper;
        this.url = url;
    }

    @Override
    public List<CityBikeStation> getClosestStations(double latitude, double longitude, int numberOfStations) {

        try {
            Response stationsResponse = okHttpClient.newCall(new Request.Builder()
                    .url(url.newBuilder().addPathSegment("api").addPathSegment("v1").addPathSegment("stations").build())
                    .build())
                    .execute();

            StationsResponse stations = jsonMapper.readValue(stationsResponse.body().byteStream(), StationsResponse.class);

            return emptyList();

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong when calling Oslo Bysykkel API", e);
        }
    }
}
