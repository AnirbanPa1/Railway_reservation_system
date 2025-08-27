package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class RailwaySystemHibernate {
    Scanner sc = new Scanner(System.in);

    public void addTrain() {
        System.out.print("Enter Train Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Source: ");
        String source = sc.nextLine();
        System.out.print("Enter Destination: ");
        String dest = sc.nextLine();
        System.out.print("Enter Total Seats: ");
        int seats = sc.nextInt();
        Route route = new Route(source, dest);
        Train train = new Train(name, route, seats);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(train);
            tx.commit();
            System.out.println("Train added successfully.");
        }
    }

    public void bookTicket() {
        sc.nextLine();
        System.out.print("Enter Passenger Name: ");
        String pname = sc.nextLine();
        System.out.println("Enter Passenger Age: ");
        int age = sc.nextInt();
        System.out.println("Enter Phone Number: ");
        long phone = sc.nextLong();
        System.out.print("Enter Train ID: ");
        int trainId = sc.nextInt();
        System.out.print("Enter Seats to Book: ");
        int seats = sc.nextInt();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Train train = session.get(Train.class, trainId);
            if (train == null) {
                System.out.println("Train not found.");
                return;
            }
            Passenger passenger = new Passenger(pname, age, phone);
            Reservation res = new Reservation(passenger, train, seats);
            session.persist(res);
            tx.commit();
            System.out.println("Reservation successful.");
        }
    }

    public void viewAllReservations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Reservation> reservations = session.createQuery("from Reservation", Reservation.class).list();
            reservations.forEach(r ->
                    System.out.printf("ID: %d, Name: %s, Train ID: %d, Seats: %d%n",
                            r.getReservationId(), r.getPassenger().getName(),
                            r.getTrain().getId(), r.getSeatsBooked())
            );
        }
    }

    public void updateReservation() {
        System.out.print("Enter Reservation ID to Update: ");
        int resId = sc.nextInt();
        System.out.print("Enter New Seats: ");
        int newSeats = sc.nextInt();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Reservation res = session.get(Reservation.class, resId);
            if (res != null) {
                res.setSeatsBooked(newSeats);
                session.merge(res);
                tx.commit();
                System.out.println("Reservation updated.");
            } else {
                System.out.println("Reservation ID not found.");
            }
        }
    }

    public void deleteReservation() {
        System.out.print("Enter Reservation ID to Delete: ");
        int resId = sc.nextInt();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Reservation res = session.get(Reservation.class, resId);
            if (res != null) {
                session.remove(res);
                tx.commit();
                System.out.println("Reservation deleted.");
            } else {
                System.out.println("Reservation not found.");
            }
        }
    }

    public void searchReservation() {
        sc.nextLine();
        System.out.print("Enter Passenger Name to Search: ");
        String name = sc.nextLine();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Reservation> results = session
                    .createQuery("from Reservation r where r.passenger.name like :name", Reservation.class)
                    .setParameter("name", "%" + name + "%")
                    .list();

            results.forEach(r ->
                    System.out.printf("ID: %d, Name: %s, Age: %d, Phone: %d, Train ID: %d, Seats: %d%n",
                            r.getReservationId(), r.getPassenger().getName(), r.getPassenger().getAge(), r.getPassenger().getPhone(),
                            r.getTrain().getId(), r.getSeatsBooked())
            );
        }
    }
}
