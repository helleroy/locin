package no.helleroy.locin.publictransport;

import java.util.List;

public class Line {

    private final String id;
    private final String name;
    private final TransportType type;
    private final List<String> deviations;

    public Line(String id, String name, TransportType type, List<String> deviations) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.deviations = deviations;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TransportType getType() {
        return type;
    }

    public List<String> getDeviations() {
        return deviations;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", deviations=" + deviations +
                '}';
    }
}
