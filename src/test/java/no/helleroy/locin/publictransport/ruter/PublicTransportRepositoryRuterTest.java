package no.helleroy.locin.publictransport.ruter;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.publictransport.Stop;
import no.helleroy.locin.publictransport.Visit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PublicTransportRepositoryRuterTest {

    private PublicTransportRepositoryRuter publicTransportRepositoryRuter;

    @Before
    public void setUp() throws Exception {
        publicTransportRepositoryRuter = new PublicTransportRepositoryRuter(
                new OkHttpClient(),
                new ObjectMapper().findAndRegisterModules(),
                HttpUrl.parse("http://reisapi.ruter.no"));
    }

    @Test
    public void getStops() throws Exception {
        List<Stop> stopsWithinArea = publicTransportRepositoryRuter.getStopsWithinAreaUTM32(598250, 599000, 6644500, 6645250);

        for (Stop stop : stopsWithinArea) {
            System.out.println(stop);
        }
    }

    @Test
    public void getVisits() throws Exception {
        List<Visit> visits = publicTransportRepositoryRuter.getVisitsForStop(new Stop("3010527", "Biermanns Gate"));

        for (Visit visit : visits) {
            System.out.println(visit);
        }
    }
}