package vista;

import modelo.Contacto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentesGraficos {

    private JFrame frame;

    private JPanel mainPanel;
    private JPanel header;
    private JPanel inputsForm;
    private JPanel buttonsLayout;
    private JPanel searchPanel;
    private JPanel contactPanel;

    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoTelefono;
    private JTextField campoBuscar;

    private JButton botonAgregar;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonBuscar;

    private List<Contacto> listaContactos = new ArrayList<>();

    public ComponentesGraficos() {

        createFrame();

        renderHeader();
        renderInputs();
        renderButtonsLayout();
        renderSearch();

        frame.setVisible(true);
    }

    private void createFrame() {

        frame = new JFrame("Agenda de Contactos");

        frame.setSize(1000, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        renderMainPanel();
    }

    private void renderMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        frame.add(mainPanel);
    }

    private void renderHeader() {

        header = new JPanel();

        JLabel titulo = new JLabel("Agenda de Contactos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.add(titulo);

        header.setMaximumSize(header.getPreferredSize());

        mainPanel.add(header);

    }

    private void renderInputs() {

        inputsForm = new JPanel();
        inputsForm.setLayout(new BoxLayout(inputsForm, BoxLayout.Y_AXIS));

        campoNombre = new JTextField(15);
        campoApellido = new JTextField(15);
        campoTelefono = new JTextField(15);

        JPanel filaNombre = new JPanel();
        filaNombre.add(new JLabel("Nombre:"));
        filaNombre.add(campoNombre);

        JPanel filaApellido = new JPanel();
        filaApellido.add(new JLabel("Apellido:"));
        filaApellido.add(campoApellido);

        JPanel filaTelefono = new JPanel();
        filaTelefono.add(new JLabel("Teléfono:"));
        filaTelefono.add(campoTelefono);

        // Agregar las filas al formulario
        inputsForm.add(filaNombre);
        inputsForm.add(filaApellido);
        inputsForm.add(filaTelefono);

        inputsForm.setMaximumSize(inputsForm.getPreferredSize());

        mainPanel.add(inputsForm);
    }

    private void renderButtonsLayout() {

        buttonsLayout = new JPanel();

        botonAgregar = new JButton("Agregar");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");

        buttonsLayout.add(botonAgregar);
        buttonsLayout.add(botonModificar);
        buttonsLayout.add(botonEliminar);

        buttonsLayout.setMaximumSize(buttonsLayout.getPreferredSize());

        mainPanel.add(buttonsLayout);
    }

    private void renderSearch() {

        searchPanel = new JPanel();

        JLabel labelBuscar = new JLabel("Buscar:");
        campoBuscar = new JTextField(15);
        botonBuscar = new JButton("Buscar");

        searchPanel.add(labelBuscar);
        searchPanel.add(campoBuscar);
        searchPanel.add(botonBuscar);

        searchPanel.setMaximumSize(searchPanel.getPreferredSize());

        mainPanel.add(searchPanel);

    }

    private void renderContacts(List<Contacto> contactos) {

        contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Lista de Contactos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setAlignmentX(Component.RIGHT_ALIGNMENT);

        contactPanel.add(titulo);

        renderCardContact(contactos);

        contactPanel.setMaximumSize(contactPanel.getPreferredSize());

        mainPanel.add(contactPanel);

    }

    private void renderCardContact(List<Contacto> contactos) {

        for (Contacto contacto : contactos) {

            JPanel card = new JPanel();
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

            JLabel nombre = new JLabel(contacto.getNombre() + " " + contacto.getApellido());
            nombre.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JLabel telefono = new JLabel("   " + contacto.getTelefono());
            telefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            telefono.setForeground(new Color(100, 100, 100));

            card.add(nombre);
            card.add(telefono);

            contactPanel.add(card);
            contactPanel.add(Box.createVerticalStrut(6));
        }
    }

    public void actualizarContactos (List<Contacto> contactos) {
        this.listaContactos = contactos;
    }



    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoApellido() {
        return campoApellido;
    }

    public JTextField getCampoTelefono() {
        return campoTelefono;
    }

    public JTextField getCampoBuscar() {
        return campoBuscar;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JPanel getContactPanel() {
        return contactPanel;
    }
}