package controlador;

import modelo.Agenda;
import modelo.Contacto;
import vista.ComponentesGraficos;

import javax.swing.*;
import java.util.Map;

public class AgendaControlador {

    // Modelo
    private Agenda agenda;

    // Vista
    private ComponentesGraficos vista;

    // Constructor
    public AgendaControlador(ComponentesGraficos vista) {

        this.vista = vista;
        this.agenda = new Agenda(vista.getCapacidadAgenda());

        vista.actualizarContactos(agenda.listarContactos());


        iniciarEventos();
    }

    private void iniciarEventos() {

        vista.getBotonAgregar().addActionListener(e -> agregarContacto());

        vista.getBotonBuscar().addActionListener(e -> buscarContacto());

        vista.getBotonEliminar().addActionListener(e -> eliminarContacto());

        vista.getBotonModificar().addActionListener(e -> modificarTelefono());
    }


    private void agregarContacto() {

        try {

            String nombre = vista.getCampoNombre().getText();
            String apellido = vista.getCampoApellido().getText();
            String telefono = vista.getCampoTelefono().getText();

            Map<String, Object> respuesta =
                    agenda.añadirContacto(
                            new Contacto(nombre, apellido, telefono)
                    );

            JOptionPane.showMessageDialog(
                    null,
                    respuesta.get("mensaje")
            );

            if ((Boolean) respuesta.get("exito")) {

                // Se actualiza la lista de contactos en pantalla
                vista.actualizarContactos(
                        agenda.listarContactos()
                );

                limpiarCampos();
            }

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage()
            );
        }
    }

    private void buscarContacto() {

        String texto = vista.getCampoBuscar().getText().trim();
        String[] partes = texto.split("\\s+", 2);
        String nombre = partes[0];
        String apellido = partes.length > 1 ? partes[1] : "";

        String contactoNumero = agenda.buscarContactoTelefono(nombre, apellido);

        if (contactoNumero != null ) {
            JOptionPane.showMessageDialog(null, contactoNumero);
        } else {
            JOptionPane.showMessageDialog(null, "Contacto no encontrado.");
        }
    }

    private void eliminarContacto() {

        String nombre = vista.getCampoNombre().getText();
        String apellido = vista.getCampoApellido().getText();

        Map<String, Object> respuesta =
                agenda.eliminarContacto(nombre, apellido);

        JOptionPane.showMessageDialog(
                null,
                respuesta.get("mensaje")
        );

        if ((Boolean) respuesta.get("exito")) {

            vista.actualizarContactos(
                    agenda.listarContactos()
            );

            limpiarCampos();
        }
    }

    private void modificarTelefono() {

        String nombre = vista.getCampoNombre().getText();
        String apellido = vista.getCampoApellido().getText();
        String telefono = vista.getCampoTelefono().getText();

        Map<String, Object> respuesta =
                agenda.modificarTelefono(
                        nombre,
                        apellido,
                        telefono
                );

        JOptionPane.showMessageDialog(
                null,
                respuesta.get("mensaje")
        );

        if ((Boolean) respuesta.get("exito")) {

            vista.actualizarContactos(
                    agenda.listarContactos()
            );

            limpiarCampos();
        }
    }

    private void limpiarCampos() {

        vista.getCampoNombre().setText("");
        vista.getCampoApellido().setText("");
        vista.getCampoTelefono().setText("");
    }

}