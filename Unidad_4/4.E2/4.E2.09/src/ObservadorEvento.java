package unidad4.E2;

public interface ObservadorEvento {

    void alRecibir(EventoSistema evento);

    default String nombre() {
        return getClass().getSimpleName();
    }
}
