package unidad4.E2;

public class ServicioAutoescalado implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        System.out.println("   [Autoescalado] Se agregan 2 nodos al cluster (causa: " + evento.cuerpo() + ")");
    }
}
