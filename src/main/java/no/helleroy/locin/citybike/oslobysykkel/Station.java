package no.helleroy.locin.citybike.oslobysykkel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    private int id;
    private String title;
    private String subtitle;
    private Coordinates center;
    private Availability availability;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Coordinates getCenter() {
        return center;
    }

    public void setCenter(Coordinates center) {
        this.center = center;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Coordinates {

        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Availability {

        private int bikes;
        private int locks;

        public int getBikes() {
            return bikes;
        }

        public void setBikes(int bikes) {
            this.bikes = bikes;
        }

        public int getLocks() {
            return locks;
        }

        public void setLocks(int locks) {
            this.locks = locks;
        }
    }
}
