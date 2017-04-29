package no.helleroy.locin.citybike.oslobysykkel;

import java.util.List;

public class Status {

    private boolean allStationsClosed;
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
