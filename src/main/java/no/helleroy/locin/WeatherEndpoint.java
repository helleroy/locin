package no.helleroy.locin;

import no.helleroy.locin.weather.WeatherForecast;
import no.helleroy.locin.weather.WeatherRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherEndpoint {

    private final WeatherRepository weatherRepository;

    public WeatherEndpoint(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @RequestMapping(value = "/{country}/{county}/{municipality}/{district}", method = RequestMethod.GET)
    public WeatherForecast getWeatherForecast(@PathVariable String country,
                                              @PathVariable String county,
                                              @PathVariable String municipality,
                                              @PathVariable String district) {

        return weatherRepository.getWeatherForecast(country, county, municipality, district);
    }
}
