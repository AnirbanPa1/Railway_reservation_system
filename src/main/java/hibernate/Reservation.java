package hibernate;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private int seatsBooked;

    @Embedded
    private Passenger passenger;

    public Reservation() {}

    public Reservation(Passenger passenger, Train train, int seatsBooked) {
        this.passenger = passenger;
        this.train = train;
        this.seatsBooked = seatsBooked;
    }

}
