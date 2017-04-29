package no.helleroy.locin.citybike.oslobysykkel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Availability {

    private int id;
    private Available availability;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Available getAvailability() {
        return availability;
    }

    public void setAvailability(Available availability) {
        this.availability = availability;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Available {

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
