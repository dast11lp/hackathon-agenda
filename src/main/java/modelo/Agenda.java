package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Agenda {

    private List<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda() {
        contactos = new ArrayList<>();
        capacidadMaxima = 10;
    }

    public Agenda(int capacidadMaxima) {
        contactos = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    private boolean validarTelefono(String telefono) {
        String regex = "^(\\+\\d{1,3}\\s?)?\\d{9,12}$";
        return Pattern.matches(regex, telefono);
    }

    private boolean campoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public boolean existeContacto(Contacto contacto) {
        return contactos.contains(contacto);
    }

    public Map<String, Object> añadirContacto(Contacto contacto) {

        Map<String, Object> respuesta = new HashMap<>();

        if (contacto == null) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El contacto es nulo.");
            return respuesta;
        }

        if (agendaLlena()) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "La agenda está llena.");
            return respuesta;
        }

        if (campoVacio(contacto.getNombre())) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El nombre es obligatorio.");
            return respuesta;
        }

        if (campoVacio(contacto.getApellido())) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El apellido es obligatorio.");
            return respuesta;
        }

        if (!validarTelefono(contacto.getTelefono())) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El teléfono tiene un formato inválido.");
            return respuesta;
        }

        if (existeContacto(contacto)) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El contacto ya existe.");
            return respuesta;
        }

        contactos.add(contacto);

        respuesta.put("exito", true);
        respuesta.put("mensaje", "Contacto agregado correctamente.");

        return respuesta;
    }

    public Contacto buscarContacto(String nombre, String apellido) {

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)
                    && contacto.getApellido().equalsIgnoreCase(apellido)) {
                return contacto;
            }
        }

        return null;
    }

    // Modifico metodo para que no solo ordene por nombre sino también por apellido
    public List<Contacto> listarContactos() {

        contactos.sort((c1, c2) -> {
            int comparacion = c1.getNombre().compareToIgnoreCase(c2.getNombre());

            if (comparacion == 0) {
                return c1.getApellido().compareToIgnoreCase(c2.getApellido());
            }

            return comparacion;
        });

        return contactos;
    }

    public Map<String, Object> modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

        Map<String, Object> respuesta = new HashMap<>();
        Contacto contacto = buscarContacto(nombre, apellido);

        if (contacto == null) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El contacto no existe.");
            return respuesta;
        }

        if (!validarTelefono(nuevoTelefono)) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El teléfono tiene un formato inválido.");
            return respuesta;
        }

        contacto.setTelefono(nuevoTelefono);

        respuesta.put("exito", true);
        respuesta.put("mensaje", "Teléfono actualizado correctamente.");

        return respuesta;
    }

    public Map<String, Object> eliminarContacto(String nombre, String apellido) {

        Map<String, Object> respuesta = new HashMap<>();

        Contacto contacto = buscarContacto(nombre, apellido);

        if (contacto == null) {
            respuesta.put("exito", false);
            respuesta.put("mensaje", "El contacto no existe.");
            return respuesta;
        }

        contactos.remove(contacto);
        respuesta.put("exito", true);
        respuesta.put("mensaje", "Contacto eliminado correctamente.");

        return respuesta;
    }

}