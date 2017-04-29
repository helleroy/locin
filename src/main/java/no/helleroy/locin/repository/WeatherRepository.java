package no.helleroy.locin.repository;

import no.helleroy.locin.domain.WeatherForecast;

public interface WeatherRepository {

    WeatherForecast getWeatherForecast(String country, String county, String municipality, String district);
}
