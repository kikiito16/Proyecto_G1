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
            //Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UPCRush", "root", "123");
        }
        catch(SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        //catch (ClassNotFoundException e)
        //{
            //System.out.println(e.toString());
        //}

        return conn;
    }

}
