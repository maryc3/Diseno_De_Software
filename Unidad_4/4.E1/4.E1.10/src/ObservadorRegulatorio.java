package unidad4.E1;

/** Subsistema regulatorio. Puede lanzar una excepción comprobada: el sujeto la aísla. */
public interface ObservadorRegulatorio {

    String nombre();

    void procesar(CambioRiesgo cambio) throws Exception;
}
