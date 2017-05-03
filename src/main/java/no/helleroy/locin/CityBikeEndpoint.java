package no.helleroy.locin;

import no.helleroy.locin.citybike.CityBikeRepository;
import no.helleroy.locin.citybike.CityBikeStation;
import no.helleroy.locin.citybike.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/citybike")
public class CityBikeEndpoint {

    private final CityBikeRepository cityBikeRepository;

    @Autowired
    public CityBikeEndpoint(CityBikeRepository cityBikeRepository) {
        this.cityBikeRepository = cityBikeRepository;
    }

    @RequestMapping("/closest")
    public List<CityBikeStation> getClosestStations(@RequestParam("latitude") double latitude,
                                                    @RequestParam("longitude") double longitude,
                                                    @RequestParam("numberOfStations") int numberOfStations) {

        return cityBikeRepository.getClosestStations(new Coordinates(latitude, longitude), numberOfStations);
    }
}
