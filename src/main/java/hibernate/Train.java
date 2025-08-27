package hibernate;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int totalSeats;

    @Embedded
    private Route route;

    public Train() {}

    public Train(String name, Route route, int totalSeats) {

        this.name = name;
        this.route = route;
        this.totalSeats = totalSeats;
    }
}
