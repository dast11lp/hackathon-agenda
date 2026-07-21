package vista;

import javax.swing.*;
import java.awt.*;

public class ComponentesGraficos {

    private JFrame frame;

    private JPanel mainPanel;
    private JPanel header;
    private JPanel inputsForm;
    private JPanel buttonsLayout;
    private JPanel contactPanel;

    public ComponentesGraficos() {

        createFrame();

        renderHeader();
        renderInputs();
        renderButtonsLayout();
        renderContacts();

        frame.setVisible(true);
    }

    private void createFrame() {

        frame = new JFrame("Agenda de Contactos");

        frame.setSize(1000, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        renderMainPanel();;
    }

    private void renderMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.add(mainPanel);
    }

    private void renderHeader() {

        header = new JPanel();

        JLabel titulo = new JLabel("Agenda de Contactos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(titulo);
        mainPanel.add(header);
    }

    private void renderInputs() {

        inputsForm = new JPanel();
        inputsForm.setLayout(new BoxLayout(inputsForm, BoxLayout.Y_AXIS));

        // ===== Nombre =====
        JPanel filaNombre = new JPanel();
        filaNombre.add(new JLabel("Nombre:"));
        filaNombre.add(new JTextField(15));

        // ===== Apellido =====
        JPanel filaApellido = new JPanel();
        filaApellido.add(new JLabel("Apellido:"));
        filaApellido.add(new JTextField(15));

        // ===== Teléfono =====
        JPanel filaTelefono = new JPanel();
        filaTelefono.add(new JLabel("Teléfono:"));
        filaTelefono.add(new JTextField(15));

        // Agregar las filas al formulario
        inputsForm.add(filaNombre);
        inputsForm.add(filaApellido);
        inputsForm.add(filaTelefono);

        mainPanel.add(inputsForm);
    }

    private void renderButtonsLayout() {

        buttonsLayout = new JPanel();

        JButton agregar = new JButton("Agregar");
        JButton modificar = new JButton("Modificar");
        JButton eliminar = new JButton("Eliminar");

        buttonsLayout.add(agregar);
        buttonsLayout.add(modificar);
        buttonsLayout.add(eliminar);

        mainPanel.add(buttonsLayout);
    }

    private void renderContacts() {

        contactPanel = new JPanel();

        JLabel titulo = new JLabel("Contactos");

        contactPanel.add(titulo);

        renderCardContact();

        mainPanel.add(contactPanel);
    }

    private void renderCardContact() {

        JLabel nombre = new JLabel("Carlos Perez");
        JLabel telefono = new JLabel("+57 3014794420");

        contactPanel.add(nombre);
        contactPanel.add(telefono);
        this.contactPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}