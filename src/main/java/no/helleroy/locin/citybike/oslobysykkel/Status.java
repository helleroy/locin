package no.helleroy.locin.citybike.oslobysykkel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    @JsonProperty("all_stations_closed")
    private boolean allStationsClosed;
    @JsonProperty("stations_closed")
    private List<Integer> stationsClosed;

    public boolean isAllStationsClosed() {
        return allStationsClosed;
    }

    public void setAllStationsClosed(boolean allStationsClosed) {
        this.allStationsClosed = allStationsClosed;
    }

    public List<Integer> getStationsClosed() {
        return stationsClosed;
    }

    public void setStationsClosed(List<Integer> stationsClosed) {
        this.stationsClosed = stationsClosed;
    }
}
