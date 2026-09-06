
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    public static Connection getConnection(String user, String password) throws Exception {
        String url = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";  //change it according to your database setup
        return DriverManager.getConnection(url, user, password);
    }
}
