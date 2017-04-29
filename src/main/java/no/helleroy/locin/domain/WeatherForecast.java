package no.helleroy.locin.domain;


import java.time.LocalDateTime;
import java.util.List;

public class WeatherForecast {

    private final Sun sun;
    private final List<ByHour> byHour;

    public WeatherForecast(Sun sun, List<ByHour> byHour) {
        this.sun = sun;
        this.byHour = byHour;
    }

    public Sun getSun() {
        return sun;
    }

    public List<ByHour> getByHour() {
        return byHour;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "sun=" + sun +
                ", byHour=" + byHour +
                '}';
    }

    public static final class Sun {

        private final LocalDateTime sunrise;
        private final LocalDateTime sunset;

        public Sun(LocalDateTime sunrise, LocalDateTime sunset) {
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public LocalDateTime getSunrise() {
            return sunrise;
        }

        public LocalDateTime getSunset() {
            return sunset;
        }

        @Override
        public String toString() {
            return "Sun{" +
                    "sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    public static final class ByHour {

        private final LocalDateTime from;
        private final LocalDateTime to;
        private final int temperature;
        private final double precipitation;
        private final Wind wind;

        public ByHour(LocalDateTime from, LocalDateTime to, int temperature, double precipitation, Wind wind) {
            this.from = from;
            this.to = to;
            this.temperature = temperature;
            this.precipitation = precipitation;
            this.wind = wind;
        }

        public LocalDateTime getFrom() {
            return from;
        }

        public LocalDateTime getTo() {
            return to;
        }

        public int getTemperature() {
            return temperature;
        }

        public double getPrecipitation() {
            return precipitation;
        }

        public Wind getWind() {
            return wind;
        }

        @Override
        public String toString() {
            return "ByHour{" +
                    "from=" + from +
                    ", to=" + to +
                    ", temperature=" + temperature +
                    ", precipitation=" + precipitation +
                    ", wind=" + wind +
                    '}';
        }

        public static final class Wind {

            private final double directionDegrees;
            private final String directionCode;
            private final double speed;

            public Wind(double directionDegrees, String directionCode, double speed) {
                this.directionDegrees = directionDegrees;
                this.directionCode = directionCode;
                this.speed = speed;
            }

            public double getDirectionDegrees() {
                return directionDegrees;
            }

            public String getDirectionCode() {
                return directionCode;
            }

            public double getSpeed() {
                return speed;
            }

            @Override
            public String toString() {
                return "Wind{" +
                        "directionDegrees=" + directionDegrees +
                        ", directionCode='" + directionCode + '\'' +
                        ", speed=" + speed +
                        '}';
            }
        }
    }
}
