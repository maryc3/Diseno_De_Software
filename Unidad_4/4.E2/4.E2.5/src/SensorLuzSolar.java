package unidad4.E2;

import java.util.concurrent.CopyOnWriteArrayList;

/** Sujeto observable. Hay un sensor por fachada; un mismo actuador puede escuchar a varios. */
public class SensorLuzSolar {

    private final String zona;
    private final CopyOnWriteArrayList<ObservadorLuz> actuadores = new CopyOnWriteArrayList<>();

    public SensorLuzSolar(String zona) {
        this.zona = zona;
    }

    public void suscribir(ObservadorLuz actuador) {
        actuadores.addIfAbsent(actuador);
    }

    public void desuscribir(ObservadorLuz actuador) {
        actuadores.remove(actuador);
    }

    /** Simula una nueva medición de luminosidad exterior. */
    public void medir(int lux) {
        System.out.println("Sensor " + zona + " mide " + lux + " lux");
        LecturaLuz lectura = new LecturaLuz(zona, lux);
        for (ObservadorLuz actuador : actuadores) {
            actuador.alCambiarLuz(lectura);
        }
    }
}
