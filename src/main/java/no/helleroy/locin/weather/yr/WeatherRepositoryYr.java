package no.helleroy.locin.weather.yr;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.helleroy.locin.weather.WeatherForecast;
import no.helleroy.locin.weather.WeatherRepository;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class WeatherRepositoryYr implements WeatherRepository {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper xmlMapper;
    private final HttpUrl url;

    public WeatherRepositoryYr(OkHttpClient okHttpClient, ObjectMapper xmlMapper, HttpUrl url) {
        this.okHttpClient = okHttpClient;
        this.xmlMapper = xmlMapper;
        this.url = url;
    }

    @Override
    public WeatherForecast getWeatherForecast(String country, String county, String municipality, String district) {

        try {
            Response response = okHttpClient
                    .newCall(new Request.Builder()
                            .url(url.newBuilder()
                                    .addPathSegment("sted")
                                    .addPathSegment(country)
                                    .addPathSegment(county)
                                    .addPathSegment(municipality)
                                    .addPathSegment(district)
                                    .addPathSegment("varsel_time_for_time.xml")
                                    .build())
                            .build())
                    .execute();

            YrWeatherdata yrWeatherdata = xmlMapper.readValue(response.body().byteStream(), YrWeatherdata.class);

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
