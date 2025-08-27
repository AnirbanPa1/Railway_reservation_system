package hibernate;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Passenger {

    private String name;
    private int age;
    private long phone;

    public Passenger() {}
    public Passenger(String name, int age, long phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
