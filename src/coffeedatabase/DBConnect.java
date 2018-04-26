/*
 * Written by Harry Silman
 */
package coffeedatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {

    private static Connection conn;
    private final static String URL = "jdbc:mysql://rds-mysql-coffee.cupi3rsaegkw.us-east-1.rds.amazonaws.com/coffee";
    private final static String USER = "root";
    private final static String PASS = "password";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }

        conn = DriverManager.getConnection(URL, USER, PASS);
        return conn;
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }
}
