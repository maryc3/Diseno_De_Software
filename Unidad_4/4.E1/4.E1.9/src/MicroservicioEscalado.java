package unidad4.E1;

public class MicroservicioEscalado implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        System.out.println("   [Escalado] Levantando una instancia adicional por: " + evento.cuerpo());
    }
}
