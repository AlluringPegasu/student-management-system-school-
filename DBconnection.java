import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection{
    
    public static Connection connect() {
        try {
        	String url = "jdbc:oracle:thin:@localhost:1521/studentdb"; 
            String user = "system"; 
            String pass = "123"; 
            return DriverManager.getConnection(url, user, pass);
            
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}