package unidad4.E1;

import java.util.concurrent.atomic.AtomicInteger;

/** Observador para la prueba de concurrencia: cuenta eventos de forma thread-safe. */
public class ContadorEventos implements ObservadorEvento {

    private final AtomicInteger total = new AtomicInteger();

    @Override
    public void alRecibir(EventoSistema evento) {
        total.incrementAndGet();
    }

    public int getTotal() {
        return total.get();
    }
}
