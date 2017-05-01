package no.helleroy.locin.publictransport.ruter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class RuterVisit {

    @JsonProperty("MonitoredVehicleJourney")
    private MonitoredVehicleJourney monitoredVehicleJourney;
    @JsonProperty("Extensions")
    private Extensions extensions;

    public MonitoredVehicleJourney getMonitoredVehicleJourney() {
        return monitoredVehicleJourney;
    }

    public void setMonitoredVehicleJourney(MonitoredVehicleJourney monitoredVehicleJourney) {
        this.monitoredVehicleJourney = monitoredVehicleJourney;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class MonitoredVehicleJourney {

        @JsonProperty("DestinationName")
        private String destinationName;
        @JsonProperty("PublishedLineName")
        private String publishedLineName;
        @JsonProperty("VehicleMode")
        private String vehicleMode;
        @JsonProperty("MonitoredCall")
        private MonitoredCall monitoredCall;

        public String getDestinationName() {
            return destinationName;
        }

        public void setDestinationName(String destinationName) {
            this.destinationName = destinationName;
        }

        public String getPublishedLineName() {
            return publishedLineName;
        }

        public void setPublishedLineName(String publishedLineName) {
            this.publishedLineName = publishedLineName;
        }

        public String getVehicleMode() {
            return vehicleMode;
        }

        public void setVehicleMode(String vehicleMode) {
            this.vehicleMode = vehicleMode;
        }

        public MonitoredCall getMonitoredCall() {
            return monitoredCall;
        }

        public void setMonitoredCall(MonitoredCall monitoredCall) {
            this.monitoredCall = monitoredCall;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class MonitoredCall {

            @JsonProperty("ExpectedDepartureTime")
            private ZonedDateTime expectedDepartureTime;

            public ZonedDateTime getExpectedDepartureTime() {
                return expectedDepartureTime;
            }

            public void setExpectedDepartureTime(ZonedDateTime expectedDepartureTime) {
                this.expectedDepartureTime = expectedDepartureTime;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Extensions {

        @JsonProperty("Deviations")
        private List<Deviation> deviations;

        public List<Deviation> getDeviations() {
            return deviations;
        }

        public void setDeviations(List<Deviation> deviations) {
            this.deviations = deviations;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class Deviation {

            @JsonProperty("Header")
            private String header;

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }
        }
    }
}
