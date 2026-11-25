package unidad4.E1;

/** Escucha los tres tipos. Está pensado para FALLAR con memoria crítica y probar el aislamiento de errores. */
public class MicroservicioNotificaciones implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        if (evento.tipo() == TipoAlerta.MEMORIA_INSUFICIENTE && evento.severidad() == Severidad.CRITICA) {
            throw new IllegalStateException("el servidor SMTP no responde");
        }
        System.out.println("   [Notificaciones] Mail enviado: " + evento.tipo() + " en " + evento.nodoOrigen());
    }
}
