package org.metrodataacademy.tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection connection;

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/db_hr";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }
}