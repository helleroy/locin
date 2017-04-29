package no.helleroy.locin.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import no.helleroy.locin.domain.WeatherForecast;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

public class WeatherRepositoryYrTest {

    private WeatherRepositoryYr weatherRepositoryYr;

    @Before
    public void setUp() throws Exception {
        weatherRepositoryYr = new WeatherRepositoryYr(new OkHttpClient(), new XmlMapper().findAndRegisterModules(), "http://www.yr.no/");
    }

    @Test
    public void getWeather() throws Exception {
        WeatherForecast weatherForecast = weatherRepositoryYr.getWeatherForecast("norway", "oslo", "oslo", "oslo");

        System.out.println(weatherForecast);
    }
}