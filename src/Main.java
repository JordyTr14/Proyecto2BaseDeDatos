import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        int opcion;
        do {

            System.out.println("\n______MENU PRINCIPAL_____:3");
            System.out.println("1. Ingreso de alumnos");
            System.out.println("2. Ingreso de notas");
            System.out.println("3. Eliminar alumnos");
            System.out.println("4. Actualizar alumnos");
            System.out.println("5. Buscar alumnos");
            System.out.println("6. Obtener promedios");
            System.out.println("7. Listar alumnos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Carnet: ");
                    String carnet = scanner.nextLine();
                    System.out.print("Nombres: ");
                    String nombres = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = scanner.nextLine();
                    System.out.print("Seccion: ");
                    String seccion = scanner.nextLine();
                    System.out.print("Nota: ");
                    double nota = scanner.nextDouble();
                    Alumno alumno = new Alumno(carnet, nombres, apellidos, seccion, nota);
                    alumnoDAO.insertarAlumno(alumno);
                    break;
                case 2:
                    System.out.print("Ingrese carnet del alumno: ");
                    String carnetNota = scanner.nextLine();
                    System.out.print("Ingrese nota: ");
                    double notaAlumno = scanner.nextDouble();
                    scanner.nextLine();
                    alumnoDAO.ingresarNota(carnetNota, notaAlumno);
                    break;
                case 3:
                    System.out.print("Ingrese carnet a eliminar: ");
                    String eliminarCarnet = scanner.nextLine();
                    System.out.print("¿Seguro que desea eliminar? (s/n): ");
                    String confirmar = scanner.nextLine();
                    if (confirmar.equalsIgnoreCase("s")) {
                        alumnoDAO.eliminarAlumno(eliminarCarnet);
                    } else {
                        System.out.println("Eliminación cancelada");
                    }
                    break;
                case 4:
                    System.out.print("Carnet del alumno: ");
                    String carnetActualizar = scanner.nextLine();
                    System.out.print("Nuevos nombres: ");
                    String nuevosNombres = scanner.nextLine();
                    System.out.print("Nuevos apellidos: ");
                    String nuevosApellidos = scanner.nextLine();
                    System.out.print("Nueva nota: ");
                    double nuevaNota = scanner.nextDouble();
                    scanner.nextLine();
                    alumnoDAO.actualizarAlumno(carnetActualizar, nuevosNombres, nuevosApellidos, nuevaNota);
                    break;
                case 5:
                    System.out.println("1. Buscar por carnet");
                    System.out.println("2. Buscar por nombre");
                    int opcionBusqueda = scanner.nextInt();
                    scanner.nextLine();
                    if (opcionBusqueda == 1) {
                        System.out.print("Ingrese carnet: ");
                        String buscarCarnet = scanner.nextLine();
                        alumnoDAO.buscarAlumno(buscarCarnet);
                    } else if (opcionBusqueda == 2) {
                        System.out.print("Ingrese nombre: ");
                        String nombreBuscar = scanner.nextLine();
                        alumnoDAO.buscarPorNombre(nombreBuscar);
                    }
                    break;
                case 6:
                    alumnoDAO.obtenerPromedios();
                    break;
                case 7:
                    System.out.print("Ingrese seccion: ");
                    String seccionLista = scanner.nextLine();
                    alumnoDAO.listarPorSeccion(seccionLista);
                    break;
                case 8:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 8);
    }
}