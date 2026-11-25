package unidad4.E2;

/** Abstracción genérica: cualquier módulo que quiera escuchar eventos de tipo E la implementa. */
@FunctionalInterface
public interface ObservadorEvento<E> {
    void alOcurrir(E evento);
}
