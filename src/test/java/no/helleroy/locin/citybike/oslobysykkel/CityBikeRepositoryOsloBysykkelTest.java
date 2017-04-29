package no.helleroy.locin.citybike.oslobysykkel;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.citybike.CityBikeStation;
import no.helleroy.locin.citybike.Coordinates;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CityBikeRepositoryOsloBysykkelTest {

    private CityBikeRepositoryOsloBysykkel cityBikeRepositoryOsloBysykkel;

    @Before
    public void setUp() throws Exception {
        cityBikeRepositoryOsloBysykkel = new CityBikeRepositoryOsloBysykkel(
                new OkHttpClient(),
                new ObjectMapper().findAndRegisterModules(),
                HttpUrl.parse("https://oslobysykkel.no/"),
                "9418739db1286ec7bc0334013e57fb51");
    }

    @Test
    public void getCityBike() throws Exception {
        List<CityBikeStation> closestStations = cityBikeRepositoryOsloBysykkel.getClosestStations(new Coordinates(59.929415, 10.763655), 3);

        for (CityBikeStation closestStation : closestStations) {
            System.out.println(closestStation);
        }
    }

}