package unidad4.E1;

public class MicroservicioCache implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        System.out.println("   [Cache] Liberando cache en " + evento.nodoOrigen() + " (" + evento.cuerpo() + ")");
    }
}
