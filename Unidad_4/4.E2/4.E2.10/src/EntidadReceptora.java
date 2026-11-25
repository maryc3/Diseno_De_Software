package unidad4.E2;

/** Entidad que debe enterarse de los cambios de riesgo (AML, legal, organismos internacionales). */
public interface EntidadReceptora {

    String nombre();

    void notificar(CambioNivelRiesgo cambio) throws Exception;
}
