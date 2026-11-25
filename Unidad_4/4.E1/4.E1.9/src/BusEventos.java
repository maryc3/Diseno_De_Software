package unidad4.E1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Bus de eventos centralizado.
 *  - Estructura indexada por tipo de alerta: el despacho es una búsqueda directa en el mapa.
 *  - Colecciones concurrentes: seguro con muchos hilos publicando y suscribiendo a la vez.
 *  - Aislamiento de fallos: si un observador lanza una excepción, los demás igual reciben el evento.
 */
public class BusEventos {

    private final Map<TipoAlerta, CopyOnWriteArrayList<ObservadorEvento>> suscriptores = new ConcurrentHashMap<>();
    private final ExecutorService ejecutor = Executors.newFixedThreadPool(4);

    public void suscribir(TipoAlerta tipo, ObservadorEvento observador) {
        suscriptores.computeIfAbsent(tipo, t -> new CopyOnWriteArrayList<>()).addIfAbsent(observador);
    }

    public void desuscribir(TipoAlerta tipo, ObservadorEvento observador) {
        List<ObservadorEvento> lista = suscriptores.get(tipo);
        if (lista != null) {
            lista.remove(observador);
        }
    }

    /** Despacho sincrónico: vuelve cuando todos los observadores del tipo terminaron. */
    public void publicar(EventoSistema evento) {
        List<ObservadorEvento> destinatarios = suscriptores.get(evento.tipo());
        if (destinatarios == null) {
            return; // nadie escucha este tipo de alerta
        }
        for (ObservadorEvento observador : destinatarios) {
            try {
                observador.alRecibir(evento);
            } catch (RuntimeException e) {
                System.out.println("   [BUS] Fallo en " + observador.nombre() + ": " + e.getMessage()
                        + " (se continua con los demas)");
            }
        }
    }

    /** Despacho asincrónico en un pool de hilos. */
    public void publicarAsincrono(EventoSistema evento) {
        ejecutor.submit(() -> publicar(evento));
    }

    /** Espera a que termine lo pendiente y libera los hilos. */
    public void cerrar() throws InterruptedException {
        ejecutor.shutdown();
        ejecutor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
