package no.helleroy.locin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import no.helleroy.locin.citybike.CityBikeRepository;
import no.helleroy.locin.citybike.oslobysykkel.CityBikeRepositoryOsloBysykkel;
import no.helleroy.locin.weather.WeatherRepository;
import no.helleroy.locin.weather.yr.WeatherRepositoryYr;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean("xmlMapper")
    public ObjectMapper xmlMapper() {
        return new XmlMapper().findAndRegisterModules();
    }

    @Bean("jsonMapper")
    @Primary
    public ObjectMapper jsonMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public WeatherRepository weatherRepository(OkHttpClient okHttpClient,
                                               @Qualifier("xmlMapper") ObjectMapper xmlMapper,
                                               @Value("${yr.url}") String url) {
        return new WeatherRepositoryYr(okHttpClient, xmlMapper, HttpUrl.parse(url));
    }

    @Bean
    public CityBikeRepository cityBikeRepository(OkHttpClient okHttpClient,
                                                 @Qualifier("jsonMapper") ObjectMapper jsonMapper,
                                                 @Value("${oslobysykkel.url}") String url,
                                                 @Value("${oslobysykkel.clientidentifier}") String clientIdentifier) {
        return new CityBikeRepositoryOsloBysykkel(okHttpClient, jsonMapper, HttpUrl.parse(url), clientIdentifier);
    }
}
