package org.injector.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public Connection connect() {
        String jdbcUrl = "jdbc:postgresql://localhost:5438/my_postgres";
        String username = "postgres";
        String password = "12345";

        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
