import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AlumnoDAO {
    public void insertarAlumno(Alumno alumno) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "INSERT INTO alumnos(carnet,nombres,apellidos,seccion,nota) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alumno.carnet);
            preparedStatement.setString(2, alumno.nombres);
            preparedStatement.setString(3, alumno.apellidos);
            preparedStatement.setString(4, alumno.seccion);
            preparedStatement.setDouble(5, alumno.nota);
            preparedStatement.executeUpdate();
            System.out.println("Alumno ingresado correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void ingresarNota(String carnet, double nota) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "UPDATE alumnos SET nota=? WHERE carnet=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, nota);
            preparedStatement.setString(2, carnet);
            preparedStatement.executeUpdate();
            System.out.println("Nota ingresada correctamente");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void eliminarAlumno(String carnet) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "DELETE FROM alumnos WHERE carnet=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carnet);
            preparedStatement.executeUpdate();
            System.out.println("Alumno eliminado");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void actualizarAlumno(String carnet, String nombres, String apellidos, double nota) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "UPDATE alumnos SET nombres=?, apellidos=?, nota=? WHERE carnet=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombres);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setDouble(3, nota);
            preparedStatement.setString(4, carnet);
            preparedStatement.executeUpdate();
            System.out.println("Alumno actualizado correctamente");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void buscarAlumno(String carnet) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "SELECT * FROM alumnos WHERE carnet=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carnet);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println("\nAlumno encontrado:");
                System.out.println(resultSet.getString("carnet") + " | " + resultSet.getString("nombres") + " | " + resultSet.getString("apellidos") + " | " + resultSet.getString("seccion") + " | " + resultSet.getDouble("nota"));
            } else {
                System.out.println("Alumno no encontrado");
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void buscarPorNombre(String nombre) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "SELECT * FROM alumnos WHERE nombres LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nombre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString("carnet") + " | " + resultSet.getString("nombres") + " | " + resultSet.getString("apellidos"));
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void listarPorSeccion(String seccion) {
        try {
            Connection connection = Conexion.conectar();
            String sql = "SELECT * FROM alumnos WHERE seccion=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seccion);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString("carnet") + " | " + resultSet.getString("nombres") + " | " + resultSet.getString("apellidos") + " | " + resultSet.getDouble("nota"));
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void obtenerPromedios() {
        try {
            Connection connection = Conexion.conectar();
            String sql = "SELECT seccion, AVG(nota) AS promedio FROM alumnos GROUP BY seccion";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\n===== PROMEDIOS =====");
            while(resultSet.next()) {
                System.out.println("Seccion: " + resultSet.getString("seccion") + " | Promedio: " + resultSet.getDouble("promedio")
                );
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
