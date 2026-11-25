package unidad4.E2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Bus de eventos para monitoreo de servidores.
 *  - Mapa indexado por tipo de alerta (enrutamiento directo).
 *  - Estructuras concurrentes para alta concurrencia.
 *  - Cada observador se ejecuta dentro de su propio try-catch; los errores se guardan en
 *    una lista de fallos en lugar de interrumpir la entrega a los demás.
 *  - publicarAsincrono devuelve un CompletableFuture para poder componer o esperar.
 */
public class BusEventos {

    private final Map<TipoAlerta, CopyOnWriteArrayList<ObservadorEvento>> suscriptores = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<FalloEntrega> fallos = new CopyOnWriteArrayList<>();
    private final ExecutorService ejecutor;

    public BusEventos(int cantidadHilos) {
        this.ejecutor = Executors.newFixedThreadPool(cantidadHilos);
    }

    public void suscribir(TipoAlerta tipo, ObservadorEvento observador) {
        suscriptores.computeIfAbsent(tipo, t -> new CopyOnWriteArrayList<>()).addIfAbsent(observador);
    }

    public void desuscribir(TipoAlerta tipo, ObservadorEvento observador) {
        List<ObservadorEvento> lista = suscriptores.get(tipo);
        if (lista != null) {
            lista.remove(observador);
        }
    }

    public void publicar(EventoSistema evento) {
        List<ObservadorEvento> destinatarios = suscriptores.get(evento.tipo());
        if (destinatarios == null) {
            return;
        }
        for (ObservadorEvento observador : destinatarios) {
            try {
                observador.alRecibir(evento);
            } catch (RuntimeException e) {
                fallos.add(new FalloEntrega(evento, observador.nombre(), e.getMessage()));
                System.out.println("   [BUS] " + observador.nombre() + " fallo: " + e.getMessage()
                        + " -> registrado, se sigue con los demas");
            }
        }
    }

    public CompletableFuture<Void> publicarAsincrono(EventoSistema evento) {
        return CompletableFuture.runAsync(() -> publicar(evento), ejecutor);
    }

    /** Copia inmutable de los fallos registrados hasta el momento. */
    public List<FalloEntrega> getFallos() {
        return List.copyOf(fallos);
    }

    public void cerrar() throws InterruptedException {
        ejecutor.shutdown();
        ejecutor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
