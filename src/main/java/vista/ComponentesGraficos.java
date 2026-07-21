package vista;

import javax.swing.*;
import java.awt.*;

public class ComponentesGraficos {

    private JFrame frame;
    private JPanel header;
    private JPanel inputsForm;
    private JPanel buttonsLayout;

    public ComponentesGraficos() {

        createFrame();

        renderHeader();
        renderInputs();

        renderButtonsLayout();

        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("Agenda de Contactos");

        frame.setSize(1000, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Layout principal del JFrame
        frame.setLayout(new FlowLayout());
    }

    private void renderHeader() {

        header = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel titulo = new JLabel("Agenda de Contactos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        header.add(titulo);

        frame.add(header, BorderLayout.NORTH);
    }

    private void renderInputs() {

        inputsForm = new JPanel();

        // 3 filas, 2 columnas
        inputsForm.setLayout(new GridLayout(3, 2, 10, 10));

        inputsForm.add(new JLabel("Nombre:"));
        inputsForm.add(new JTextField());

        inputsForm.add(new JLabel("Apellido:"));
        inputsForm.add(new JTextField());

        inputsForm.add(new JLabel("Teléfono:"));
        inputsForm.add(new JTextField());

        frame.add(inputsForm, BorderLayout.CENTER);
    }


    public void renderButtonsLayout () {
        this.buttonsLayout = new JPanel();

        JButton addContact = new JButton("Agregar");
        JButton removeContact = new JButton("Modificar");
        JButton updateContact = new JButton("Eliminar");

        this.buttonsLayout.add(addContact);
        this.buttonsLayout.add(removeContact);
        this.buttonsLayout.add(updateContact);

        this.frame.add(buttonsLayout);

    }
}