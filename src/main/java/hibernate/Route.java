package hibernate;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Route {

    private String source;
    private String destination;

    public Route() {}

    public Route(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }
}
