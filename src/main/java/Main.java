import modelo.Agenda;
import modelo.Contacto;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcion;

        do {

            System.out.println("\n╔═════════════════════════════════════╗");
            System.out.println("║          AGENDA TELEFÓNICA          ║");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║ 1. Agregar contacto                 ║");
            System.out.println("║ 2. Buscar contacto                  ║");
            System.out.println("║ 3. Listar contactos                 ║");
            System.out.println("║ 4. Eliminar contacto                ║");
            System.out.println("║ 5. Modificar teléfono               ║");
            System.out.println("║ 6. Verificar si existe contacto     ║");
            System.out.println("║ 7. Verificar si la agenda está llena║");
            System.out.println("║ 8. Mostrar espacios disponibles     ║");
            System.out.println("║ 9. Salir                            ║");
            System.out.println("╚═════════════════════════════════════╝");

            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese una opción válida: ");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    System.out.println("\n----- AGREGAR CONTACTO -----");

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();

                    Contacto nuevo = new Contacto(nombre, apellido, telefono);

                    Map<String, Object> respuesta = agenda.añadirContacto(nuevo);

                    System.out.println(respuesta.get("mensaje"));

                    break;

                case 2:

                    System.out.println("\n----- BUSCAR CONTACTO -----");

                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();

                    Contacto encontrado = agenda.buscarContacto(nombre, apellido);

                    if (encontrado != null) {
                        System.out.println("Teléfono: " + encontrado.getTelefono());
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }

                    break;

                case 3:

                    System.out.println("\n----- LISTA DE CONTACTOS -----");

                    List<Contacto> lista = agenda.listarContactos();

                    if (lista.isEmpty()) {
                        System.out.println("No hay contactos registrados.");
                    } else {

                        for (Contacto contacto : lista) {

                            System.out.println(
                                    contacto.getNombre()
                                            + " "
                                            + contacto.getApellido()
                                            + " - "
                                            + contacto.getTelefono()
                            );
                        }
                    }
                    System.out.println();

                    break;
                case 4:

                    System.out.println("\n----- ELIMINAR CONTACTO -----");

                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();

                    respuesta = agenda.eliminarContacto(nombre, apellido);

                    System.out.println(respuesta.get("mensaje"));

                    break;

                case 5:

                    System.out.println("\n----- MODIFICAR TELÉFONO -----");

                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();

                    System.out.print("Nuevo teléfono: ");
                    telefono = scanner.nextLine();

                    respuesta = agenda.modificarTelefono(nombre, apellido, telefono);

                    System.out.println(respuesta.get("mensaje"));

                    break;

                case 6:

                    System.out.println("\n----- VERIFICAR CONTACTO -----");

                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();

                    Contacto contacto = agenda.buscarContacto(nombre, apellido);

                    if (contacto != null) {
                        System.out.println("El contacto existe.");
                    } else {
                        System.out.println("El contacto no existe.");
                    }

                    break;

                case 7:

                    if (agenda.agendaLlena()) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.println("Aún hay espacio disponible.");
                    }

                    break;

                case 8:

                    System.out.println(
                            "Espacios disponibles: "
                                    + agenda.espaciosLibres()
                    );

                    break;

                case 9:

                    System.out.println("Gracias por utilizar la Agenda Telefónica.");

                    break;

                default:

                    System.out.println("Opción no válida.");

            }

        } while (opcion != 9);

        scanner.close();

    }

}