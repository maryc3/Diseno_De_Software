package unidad4.E1.Ejercicio2;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable: guarda el estado central del reproductor y encapsula
 * la gestión (segura) de la lista de observadores.
 */
public class MotorReproduccion {

    // CopyOnWriteArrayList: se puede suscribir/desuscribir mientras se notifica sin romper la iteración.
    private final CopyOnWriteArrayList<ObservadorReproductor> observadores = new CopyOnWriteArrayList<>();
    private EstadoReproduccion estado = EstadoReproduccion.DETENIDO;
    private String titulo = "(sin cancion)";

    public void suscribir(ObservadorReproductor observador) {
        if (observador != null) {
            observadores.addIfAbsent(observador);
        }
    }

    public void desuscribir(ObservadorReproductor observador) {
        observadores.remove(observador);
    }

    public synchronized void cambiarEstado(EstadoReproduccion nuevoEstado) {
        establecer(nuevoEstado, this.titulo);
    }

    public synchronized void cambiarCancion(String nuevoTitulo) {
        establecer(this.estado, nuevoTitulo);
    }

    /** Cambia canción y estado a la vez, con una sola notificación. */
    public synchronized void reproducir(String nuevoTitulo) {
        establecer(EstadoReproduccion.REPRODUCIENDO, nuevoTitulo);
    }

    private void establecer(EstadoReproduccion nuevoEstado, String nuevoTitulo) {
        this.estado = nuevoEstado;
        this.titulo = nuevoTitulo;
        notificar();
    }

    private void notificar() {
        for (ObservadorReproductor observador : observadores) {
            observador.actualizar(estado, titulo);
        }
    }
}
