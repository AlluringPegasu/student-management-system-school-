import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection{
    
    public static Connection connect() {
        try {
        	String url = "jdbc:oracle:thin:@localhost:1521/studentdb"; 
            String user = "enter your username here"; 
            String pass = "enter your password here"; 
            return DriverManager.getConnection(url, user, pass);
            
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
