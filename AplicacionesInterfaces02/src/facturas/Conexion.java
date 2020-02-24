package facturas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static String url = "jdbc:hsqldb:hsql://localhost/testdb";    
    private static String driverName = "org.hsqldb.jdbcDriver";
    private static String username = "sa";   
    private static String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }                                                                                    
        return con;                                                                          
    }
}
