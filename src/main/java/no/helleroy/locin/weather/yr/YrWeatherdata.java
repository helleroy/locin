package no.helleroy.locin.weather.yr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

@JacksonXmlRootElement(localName = "weatherdata")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class YrWeatherdata {

    @JacksonXmlProperty(localName = "sun")
    private Sun sun = new Sun();
    @JacksonXmlProperty(localName = "forecast")
    private Forecast forecast = new Forecast();

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Sun {

        @JacksonXmlProperty(localName = "rise", isAttribute = true)
        private LocalDateTime sunrise;
        @JacksonXmlProperty(localName = "set", isAttribute = true)
        private LocalDateTime sunset;

        public LocalDateTime getSunrise() {
            return sunrise;
        }

        public void setSunrise(LocalDateTime sunrise) {
            this.sunrise = sunrise;
        }

        public LocalDateTime getSunset() {
            return sunset;
        }

        public void setSunset(LocalDateTime sunset) {
            this.sunset = sunset;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Forecast {

        @JacksonXmlElementWrapper(localName = "tabular")
        @JacksonXmlProperty(localName = "time")
        private List<Time> tabular = emptyList();

        public List<Time> getTabular() {
            return tabular;
        }

        public void setTabular(List<Time> tabular) {
            this.tabular = tabular;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Time {

        @JacksonXmlProperty(localName = "from", isAttribute = true)
        private LocalDateTime from;
        @JacksonXmlProperty(localName = "to", isAttribute = true)
        private LocalDateTime to;
        @JacksonXmlProperty(localName = "precipitation")
        private Percipetation precipitation;
        @JacksonXmlProperty(localName = "windDirection")
        private WindDirection windDirection;
        @JacksonXmlProperty(localName = "windSpeed")
        private WindSpeed windSpeed;
        @JacksonXmlProperty(localName = "temperature")
        private Temperature temperature;

        public LocalDateTime getFrom() {
            return from;
        }

        public void setFrom(LocalDateTime from) {
            this.from = from;
        }

        public LocalDateTime getTo() {
            return to;
        }

        public void setTo(LocalDateTime to) {
            this.to = to;
        }

        public Percipetation getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(Percipetation precipitation) {
            this.precipitation = precipitation;
        }

        public WindDirection getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(WindDirection windDirection) {
            this.windDirection = windDirection;
        }

        public WindSpeed getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(WindSpeed windSpeed) {
            this.windSpeed = windSpeed;
        }

        public Temperature getTemperature() {
            return temperature;
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class Percipetation {

            @JacksonXmlProperty(localName = "value", isAttribute = true)
            private double value;

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class WindDirection {

            @JacksonXmlProperty(localName = "deg", isAttribute = true)
            private double degrees;
            @JacksonXmlProperty(localName = "code", isAttribute = true)
            private String code;

            public double getDegrees() {
                return degrees;
            }

            public void setDegrees(double degrees) {
                this.degrees = degrees;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class WindSpeed {

            @JacksonXmlProperty(localName = "mps", isAttribute = true)
            private double mps;

            public double getMps() {
                return mps;
            }

            public void setMps(double mps) {
                this.mps = mps;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class Temperature {

            @JacksonXmlProperty(localName = "value", isAttribute = true)
            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

    }
}
