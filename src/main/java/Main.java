import controlador.AgendaControlador;
import vista.ComponentesGraficos;

public class Main {
    public static void main(String[] args) {

        ComponentesGraficos vista = new ComponentesGraficos();
        new AgendaControlador(vista);
    }
}