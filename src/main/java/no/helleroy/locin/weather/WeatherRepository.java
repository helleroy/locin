package no.helleroy.locin.weather;

public interface WeatherRepository {

    WeatherForecast getWeatherForecast(String country, String county, String municipality, String district);
}
