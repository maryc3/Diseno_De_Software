package unidad4.E2;

public class ServicioLimpiezaMemoria implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        System.out.println("   [LimpiezaMemoria] Reinicio de procesos pesados en " + evento.nodoOrigen());
    }
}
