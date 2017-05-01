package no.helleroy.locin.publictransport.ruter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class RuterStop {

    @JsonProperty("ID")
    private String id;
    @JsonProperty("Name")
    private String name;

    public String getId() {
        return id;
    }

    public RuterStop setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RuterStop setName(String name) {
        this.name = name;
        return this;
    }
}
