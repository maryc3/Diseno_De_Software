package unidad4.E1;

public interface ObservadorEvento {

    void alRecibir(EventoSistema evento);

    /** Nombre legible para los mensajes de error del bus. */
    default String nombre() {
        return getClass().getSimpleName();
    }
}
