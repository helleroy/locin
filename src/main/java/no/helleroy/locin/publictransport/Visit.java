package no.helleroy.locin.publictransport;

import java.time.ZonedDateTime;

public class Visit {

    private final Line line;
    private final Stop stop;
    private final ZonedDateTime expectedDepartureTime;

    public Visit(Line line, Stop stop, ZonedDateTime expectedDepartureTime) {
        this.line = line;
        this.stop = stop;
        this.expectedDepartureTime = expectedDepartureTime;
    }

    public Line getLine() {
        return line;
    }

    public Stop getStop() {
        return stop;
    }

    public ZonedDateTime getExpectedDepartureTime() {
        return expectedDepartureTime;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "line=" + line +
                ", stop=" + stop +
                ", expectedDepartureTime=" + expectedDepartureTime +
                '}';
    }
}
