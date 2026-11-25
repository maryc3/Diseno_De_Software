package unidad4.E1;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable. Hay un sensor por zona; un mismo observador puede estar
 * suscrito a varios sensores a la vez. El sensor no conoce ningún sistema de respuesta concreto.
 */
public class SensorHumo {

    private final String id;
    private final int piso;
    private final String zona;
    private final CopyOnWriteArrayList<ObservadorAlarma> observadores = new CopyOnWriteArrayList<>();

    public SensorHumo(String id, int piso, String zona) {
        this.id = id;
        this.piso = piso;
        this.zona = zona;
    }

    public void suscribir(ObservadorAlarma observador) {
        observadores.addIfAbsent(observador);
    }

    public void desuscribir(ObservadorAlarma observador) {
        observadores.remove(observador);
    }

    /** Simula la detección de humo y emite la señal de emergencia. */
    public void detectarHumo() {
        System.out.println("!!! Sensor " + id + " detecto humo en piso " + piso + ", " + zona);
        AlertaHumo alerta = new AlertaHumo(id, piso, zona);
        for (ObservadorAlarma observador : observadores) {
            observador.alDetectarHumo(alerta);
        }
    }
}
