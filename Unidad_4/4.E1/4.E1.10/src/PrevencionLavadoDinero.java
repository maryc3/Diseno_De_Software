package unidad4.E1;

/** Falla a propósito cuando el nuevo nivel es CRITICO, para demostrar el aislamiento de errores. */
public class PrevencionLavadoDinero implements ObservadorRegulatorio {

    @Override
    public String nombre() {
        return "Prevencion de Lavado de Dinero";
    }

    @Override
    public void procesar(CambioRiesgo cambio) throws Exception {
        if (cambio.nivelNuevo() == NivelRiesgo.CRITICO) {
            throw new Exception("motor de reglas AML no disponible");
        }
        System.out.println("   [AML] Analisis de transacciones de " + cambio.idCliente() + " completado");
    }
}
