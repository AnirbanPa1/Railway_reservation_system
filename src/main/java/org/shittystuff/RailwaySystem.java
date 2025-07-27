package org.shittystuff;

import org.shittystuff.models.Reservation;
import org.shittystuff.models.Train;

import java.util.Scanner;

public class RailwaySystem {
    Scanner sc = new Scanner(System.in);

    public void addTrain() {
        Train t = new Train();
        System.out.print("Enter Train ID: ");
        t.id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Train Name: ");
        t.name = sc.nextLine();
        System.out.print("Enter Source: ");
        t.source = sc.nextLine();
        System.out.print("Enter Destination: ");
        t.destination = sc.nextLine();
        System.out.print("Enter Total Seats: ");
        t.seats = sc.nextInt();

        Train.save(t);
    }

    public void bookTicket() {
        var r = new Reservation();
        System.out.print("Enter Passenger Name: ");
        r.passengerName = sc.nextLine();
        System.out.print("Enter Train ID: ");
        r.trainId = sc.nextInt();
        System.out.print("Enter Seats to Book: ");
        r.seatsBooked = sc.nextInt();

        Reservation.save(r);
    }

    public void viewAllReservations() {
        System.out.println("All Reservations:");
        var reservations = Reservation.getAll();
        for (var rs : reservations) {
            System.out.printf("ID: %d, Name: %s, Train ID: %d, Seats: %d%n",
                rs.id,
                rs.passengerName,
                rs.trainId,
                rs.seatsBooked)
            ;
        }
    }

    public void updateReservation() {
        var r = new Reservation();
        System.out.print("Enter Reservation ID to Update: ");
        r.id = sc.nextInt();
        System.out.print("Enter New Seats: ");
        r.seatsBooked = sc.nextInt();

        boolean updated = Reservation.update(r);
        if (updated) {
            System.out.println("Reservation updated.");
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    public void deleteReservation() {
        System.out.print("Enter Reservation ID to Delete: ");
        int resId = sc.nextInt();

        boolean deleted = Reservation.deleteById(resId);
        if (deleted) {
            System.out.println("Reservation deleted.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void searchReservation() {
        sc.nextLine();
        System.out.print("Enter Passenger Name to Search: ");
        String name = sc.nextLine();

        var reservation = Reservation.getReservationsByPassengerName(name);
        System.out.println("Search Results:");
        for (var rs : reservation) {
            System.out.printf("ID: %d, Name: %s, Train ID: %d, Seats: %d%n",
                rs.id,
                rs.passengerName,
                rs.trainId,
                rs.seatsBooked
            );
        }
    }
}
