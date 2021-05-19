package edu.upc.dsa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SessionFactory {

    public static Session openSession() {
        Connection conn = getConnection();
        Session session = new SessionImpl(conn);

        return session;
    }


    private static Connection getConnection() {
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");
        }
        catch(SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return conn;
    }

}
