package org.shittystuff;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RailwaySystem rs = new RailwaySystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n||====== Railway Reservation Menu ======||");
            System.out.println("|| 1. Add Train                         ||");
            System.out.println("|| 2. Book Ticket                       ||");
            System.out.println("|| 3. View All Reservations             ||");
            System.out.println("|| 4. Update Reservation                ||");
            System.out.println("|| 5. Delete Reservation                ||");
            System.out.println("|| 6. Search Reservation by Name        ||");
            System.out.println("|| 0. Exit                              ||");
            System.out.println("|| =====================================||");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> rs.addTrain();
                case 2 -> rs.bookTicket();
                case 3 -> rs.viewAllReservations();
                case 4 -> rs.updateReservation();
                case 5 -> rs.deleteReservation();
                case 6 -> rs.searchReservation();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
