package unidad4.E1;

public class MicroservicioRespaldo implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        System.out.println("   [Respaldo] Backup de emergencia iniciado en " + evento.nodoOrigen());
    }
}
