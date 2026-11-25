package unidad4.E2;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable. Encapsula su lista de suscriptores (privada, concurrente) y
 * solo notifica cuando el estado REALMENTE cambia.
 */
public class MonitorRed {

    private final CopyOnWriteArrayList<ObservadorRed> suscriptores = new CopyOnWriteArrayList<>();
    private EstadoRed estado = EstadoRed.DESCONECTADO;

    public void suscribir(ObservadorRed observador) {
        suscriptores.addIfAbsent(observador);
    }

    public void desuscribir(ObservadorRed observador) {
        suscriptores.remove(observador);
    }

    public synchronized void cambiarEstado(EstadoRed nuevo) {
        if (nuevo == estado) {
            return; // sin cambio: no se molesta a nadie
        }
        EstadoRed anterior = this.estado;
        this.estado = nuevo;
        for (ObservadorRed suscriptor : suscriptores) {
            suscriptor.alCambiarEstado(anterior, nuevo);
        }
    }

    public EstadoRed getEstado() {
        return estado;
    }
}
