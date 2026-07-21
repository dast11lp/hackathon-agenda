package vista;

import modelo.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    private static final int CAPACIDAD_POR_DEFECTO = 10;
    private int capacidadAgenda;

    public ComponentesGraficos() {

        preguntarCapacidad();

        createFrame();

        renderHeader();
        renderInputs();
        renderButtonsLayout();
        renderSearch();

        List<Contacto> contactosPrueba = Arrays.asList(
                new Contacto("Pepito", "Perez", "+573014794421"),
                new Contacto("Ana", "Lopez", "+573009876543"),
                new Contacto("Marta", "Gomez", "+573151234567")
        );

        renderContacts(contactosPrueba);

        frame.setVisible(true);
    }

    private void preguntarCapacidad() {

        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas usar el tamaño por defecto de la agenda (" + CAPACIDAD_POR_DEFECTO + ")?",
                "Tamaño de la agenda",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            capacidadAgenda = CAPACIDAD_POR_DEFECTO;
            return;
        }

        boolean valido = false;

        while (!valido) {
            String entrada = JOptionPane.showInputDialog(
                    null,
                    "Ingresa el tamaño que deseas para la agenda (mínimo 1):",
                    "Tamaño personalizado",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (entrada == null) {
                System.exit(0);;
            }

            try {
                int numero = Integer.parseInt(entrada.trim());
                if (numero >= 1 && numero <=20) {
                    capacidadAgenda = numero;
                    valido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El tamaño debe ser mayor o igual a 1 y menor o igual a 20");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un número válido.");
            }
        }
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
        header.setBackground(new Color(43, 76, 126));
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JLabel titulo = new JLabel("Agenda de Contactos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        header.add(titulo);

        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

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

        botonAgregar.setBackground(new Color(46, 139, 87));   // verde
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.setOpaque(true);
        botonAgregar.setBorderPainted(false);
        botonAgregar.setFocusPainted(false);

        botonModificar.setBackground(new Color(255, 165, 0)); // naranja
        botonModificar.setForeground(Color.WHITE);
        botonModificar.setOpaque(true);
        botonModificar.setBorderPainted(false);
        botonModificar.setFocusPainted(false);

        botonEliminar.setBackground(new Color(200, 30, 30));  // rojo
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setOpaque(true);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setFocusPainted(false);

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
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        contactPanel.add(titulo);

        String[] columnas = {"Nombre", "Apellido", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setRowHeight(24);
        tablaContactos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaContactos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));


        tablaContactos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tablaContactos.getSelectedRow();
                if (fila != -1) {
                    campoNombre.setText((String) modeloTabla.getValueAt(fila, 0));
                    campoApellido.setText((String) modeloTabla.getValueAt(fila, 1));
                    campoTelefono.setText((String) modeloTabla.getValueAt(fila, 2));
                }
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaContactos);
        scrollTabla.setPreferredSize(new Dimension(600, 200));
        scrollTabla.setAlignmentX(Component.LEFT_ALIGNMENT);

        contactPanel.add(scrollTabla);

        cargarFilas(contactos);

        mainPanel.add(contactPanel);

    }

    private void cargarFilas(List<Contacto> contactos) {
        for (Contacto contacto : contactos) {
            modeloTabla.addRow(new Object[]{
                    contacto.getNombre(),
                    contacto.getApellido(),
                    contacto.getTelefono()
            });
        }
    }


    public void actualizarContactos(List<Contacto> contactos) {
        modeloTabla.setRowCount(0);
        cargarFilas(contactos);
    }

    public String getContactoSeleccionado() {
        int fila = tablaContactos.getSelectedRow();
        if (fila == -1) return null;

        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        String apellido = (String) modeloTabla.getValueAt(fila, 1);

        return nombre + " " + apellido;
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

    public JTable getTablaContactos() {
        return tablaContactos;
    }

    public int getCapacidadAgenda() {
        return capacidadAgenda;
    }
}