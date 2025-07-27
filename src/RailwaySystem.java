import java.sql.*;
import java.util.Scanner;

public class RailwaySystem {
    Scanner sc = new Scanner(System.in);

    public void addTrain() {
        System.out.print("Enter Train ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Train Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Source: ");
        String source = sc.nextLine();
        System.out.print("Enter Destination: ");
        String dest = sc.nextLine();
        System.out.print("Enter Total Seats: ");
        int seats = sc.nextInt();

        String query = "INSERT INTO train VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, source);
            ps.setString(4, dest);
            ps.setInt(5, seats);
            ps.executeUpdate();
            System.out.println("Train added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookTicket() {
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train ID: ");
        int trainId = sc.nextInt();
        System.out.print("Enter Seats to Book: ");
        int seats = sc.nextInt();

        String query = "INSERT INTO reservation (passenger_name, train_id, seats_booked) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, trainId);
            ps.setInt(3, seats);
            ps.executeUpdate();
            System.out.println("Reservation successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllReservations() {
        String query = "SELECT * FROM reservation";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("All Reservations:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Train ID: %d, Seats: %d%n",
                        rs.getInt("reservation_id"),
                        rs.getString("passenger_name"),
                        rs.getInt("train_id"),
                        rs.getInt("seats_booked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReservation() {
        System.out.print("Enter Reservation ID to Update: ");
        int resId = sc.nextInt();
        System.out.print("Enter New Seats: ");
        int newSeats = sc.nextInt();

        String query = "UPDATE reservation SET seats_booked = ? WHERE reservation_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, newSeats);
            ps.setInt(2, resId);
            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Reservation updated.");
            } else {
                System.out.println("Reservation ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation() {
        System.out.print("Enter Reservation ID to Delete: ");
        int resId = sc.nextInt();

        String query = "DELETE FROM reservation WHERE reservation_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, resId);
            int deleted = ps.executeUpdate();
            if (deleted > 0) {
                System.out.println("Reservation deleted.");
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchReservation() {
        sc.nextLine();
        System.out.print("Enter Passenger Name to Search: ");
        String name = sc.nextLine();

        String query = "SELECT * FROM reservation WHERE passenger_name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            System.out.println("Search Results:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Train ID: %d, Seats: %d%n",
                        rs.getInt("reservation_id"),
                        rs.getString("passenger_name"),
                        rs.getInt("train_id"),
                        rs.getInt("seats_booked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
