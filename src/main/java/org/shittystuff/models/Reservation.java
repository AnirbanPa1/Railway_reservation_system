package org.shittystuff.models;

import org.shittystuff.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    public int id;
    public String passengerName;
    public int trainId;
    public int seatsBooked;

    public Reservation() {}

    public Reservation(int id, String passengerName, int trainId, int seatsBooked) {
        this.id = id;
        this.passengerName = passengerName;
        this.trainId = trainId;
        this.seatsBooked = seatsBooked;
    }

    private static final String ADD_QUERY = "INSERT INTO reservation (passenger_name, train_id, seats_booked) VALUES (?, ?, ?)";
    private static final String GET_ALL_QUERY = "SELECT * FROM reservation";
    private static final String UPDATE_QUERY = "UPDATE reservation SET seats_booked = ? WHERE reservation_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM reservation WHERE reservation_id = ?";
    private static final String FIND_RESERVATION_BY_PASSENGER_NAME_QUERY = "SELECT * FROM reservation WHERE passenger_name LIKE ?";
    
    public static void save(Reservation r) {
        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
            statement.setString(1, r.passengerName);
            statement.setInt(2, r.trainId);
            statement.setInt(3, r.seatsBooked);
        } catch (SQLException e) {
            System.out.println("ERROR: error while getting connection for db");
        }
    }

    public static List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(GET_ALL_QUERY)) {
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("reservation_id"), rs.getString("passenger_name"), rs.getInt("train_id"), rs.getInt("seats_booked")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean update(Reservation r) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY)) {
            ps.setInt(1, r.seatsBooked);
            ps.setInt(2, r.id);
            int updated = ps.executeUpdate();
            if (updated > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Reservation> getReservationsByPassengerName(String name) {
        List<Reservation> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_RESERVATION_BY_PASSENGER_NAME_QUERY)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("reservation_id"), rs.getString("passenger_name"), rs.getInt("train_id"), rs.getInt("seats_booked")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
