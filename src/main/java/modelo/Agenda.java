package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Agenda {

    private List<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda(){
        contactos = new ArrayList<>();
        capacidadMaxima = 10;
    }

    public Agenda(int capacidadMaxima){
        contactos = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public int espaciosLibres(){
        return capacidadMaxima - contactos.size();
    }

    public boolean agendaLlena(){
        return contactos.size() >= capacidadMaxima;
    }

    private boolean validarTelefono(String telefono) {
        String regex = "^(\\+\\d{1,3}\\s?)?\\d{9,12}$";
        return Pattern.matches(regex, telefono);
    }

    public Map<String , Object > añadirContacto(Contacto contacto) {

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

    public boolean existeContacto(Contacto contacto) {
        return contactos.contains(contacto);
    }

    public void listarContactos() {

        if (contactos.isEmpty()) {
            System.out.println("La agenda no tiene contactos registrados.");
            return;
        }

        for (Contacto contacto : contactos) {
            System.out.println(contacto);
        }

    }

    public Contacto buscaContacto(String dato) {

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(dato) || contacto.getApellido().equalsIgnoreCase(dato)
                    || contacto.getTelefono().equals(dato)) {

                return contacto;
            }

        }

        return null;
    }

}


