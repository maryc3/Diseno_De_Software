package unidad4.E2;

public class ServicioRespaldoDisco implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        System.out.println("   [RespaldoDisco] Copia de seguridad urgente de " + evento.nodoOrigen());
    }
}
