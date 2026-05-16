import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    public static Connection conectar() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdnotas", "root", "abc123");
            return connection;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}