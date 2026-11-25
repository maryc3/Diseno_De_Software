package unidad4.E2;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Bus de eventos genérico. Quien publica solo conoce al bus; quien escucha solo conoce
 * la interfaz ObservadorEvento. Nadie conoce a nadie de forma directa.
 */
public class BusEventos<E> {

    private final CopyOnWriteArrayList<ObservadorEvento<E>> oyentes = new CopyOnWriteArrayList<>();

    public void suscribir(ObservadorEvento<E> oyente) {
        oyentes.addIfAbsent(oyente);
    }

    public void desuscribir(ObservadorEvento<E> oyente) {
        oyentes.remove(oyente);
    }

    public void publicar(E evento) {
        for (ObservadorEvento<E> oyente : oyentes) {
            oyente.alOcurrir(evento);
        }
    }
}
