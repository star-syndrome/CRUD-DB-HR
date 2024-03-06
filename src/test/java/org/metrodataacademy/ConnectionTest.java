package org.metrodataacademy;

import org.junit.jupiter.api.Test;
import org.metrodataacademy.tools.DBConnection;

public class ConnectionTest {

    @Test
    void testConnection() {
        DBConnection dbConnection = new DBConnection();
        System.out.println(dbConnection.getConnection());
    }
}