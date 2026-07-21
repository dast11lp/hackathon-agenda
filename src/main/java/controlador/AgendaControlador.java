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
        this.agenda = new Agenda();

        // Cargar la lista inicialmente (vacía)
        vista.actualizarContactos(agenda.listarContactos());

        // Conectar los botones
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

        String nombre = vista.getCampoNombre().getText();
        String apellido = vista.getCampoApellido().getText();

        Contacto contacto = agenda.buscarContacto(nombre, apellido);

        if (contacto != null) {

            JOptionPane.showMessageDialog(
                    null,
                    contacto.toString()
            );

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Contacto no encontrado."
            );
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