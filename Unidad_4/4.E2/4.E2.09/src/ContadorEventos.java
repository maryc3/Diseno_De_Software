package unidad4.E2;

import java.util.concurrent.atomic.AtomicInteger;

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
