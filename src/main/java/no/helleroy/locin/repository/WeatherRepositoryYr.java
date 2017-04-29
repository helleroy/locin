package no.helleroy.locin.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.domain.WeatherForecast;
import no.helleroy.locin.dto.YrWeatherdata;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class WeatherRepositoryYr implements WeatherRepository {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper xmlMapper;
    private final String yrURL;

    public WeatherRepositoryYr(OkHttpClient okHttpClient, ObjectMapper xmlMapper, String yrURL) {
        this.okHttpClient = okHttpClient;
        this.xmlMapper = xmlMapper;
        this.yrURL = yrURL;
    }

    @Override
    public WeatherForecast getWeatherForecast(String country, String county, String municipality, String district) {

        try {
            Response response = okHttpClient
                    .newCall(new Request.Builder()
                            .url(yrURL + "/sted/" + country + "/" + county + "/" + municipality + "/" + district + "/varsel_time_for_time.xml")
                            .build())
                    .execute();

            YrWeatherdata yrWeatherdata = xmlMapper.readValue(response.body().bytes(), YrWeatherdata.class);

            WeatherForecast.Sun sun = new WeatherForecast.Sun(
                    yrWeatherdata.getSun().getSunrise(),
                    yrWeatherdata.getSun().getSunset());

            List<WeatherForecast.ByHour> byHour = yrWeatherdata
                    .getForecast()
                    .getTabular()
                    .stream()
                    .map(time -> new WeatherForecast.ByHour(
                            time.getFrom(),
                            time.getTo(),
                            time.getTemperature().getValue(),
                            time.getPrecipitation().getValue(),
                            new WeatherForecast.ByHour.Wind(
                                    time.getWindDirection().getDegrees(),
                                    time.getWindDirection().getCode(),
                                    time.getWindSpeed().getMps())))
                    .collect(toList());

            return new WeatherForecast(sun, byHour);

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong when calling YR API", e);
        }
    }
}
