package no.helleroy.locin.publictransport.ruter;

import no.helleroy.locin.publictransport.TransportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class VehicleMode {

    private static final Logger LOG = LoggerFactory.getLogger(VehicleMode.class);

    private final int mode;

    public VehicleMode(String mode) {
        this.mode = Integer.parseInt(mode);
    }

    public Optional<TransportType> toTransportType() {
        switch (mode) {
            case 0:
                return Optional.of(TransportType.BUS);
            case 1:
                return Optional.of(TransportType.FERRY);
            case 2:
                return Optional.of(TransportType.TRAIN);
            case 3:
                return Optional.of(TransportType.TRAM);
            case 4:
                return Optional.of(TransportType.METRO);
            default:
                LOG.error("Received unknown VehicleMode from Ruter: [{}]", mode);
                return Optional.empty();
        }
    }
}
