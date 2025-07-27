package org.shittystuff.models;

import org.shittystuff.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Train {
    public int id;
    public String name;
    public String source;
    public String destination;
    public int seats;


    private static final String ADD_QUERY = "INSERT INTO train VALUES (?, ?, ?, ?, ?)";

    public static void save(Train t) {
        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(ADD_QUERY);
            statement.setInt(1, t.id);
            statement.setString(2, t.name);
            statement.setString(3, t.source);
            statement.setString(4, t.destination);
            statement.setInt(5, t.seats);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("ERROR: error while getting connection for db");
        }
    }
}
