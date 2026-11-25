package unidad4.E2;

/**
 * Contrato común. Al tener un único método abstracto es una interfaz funcional:
 * se pueden registrar clases, lambdas o referencias a métodos (this::metodo).
 */
@FunctionalInterface
public interface OyenteClic {
    void alHacerClic(EventoClic evento);
}
