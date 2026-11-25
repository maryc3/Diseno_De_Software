package unidad4.E2;

public class PrevencionLavadoDinero implements EntidadReceptora {

    @Override
    public String nombre() {
        return "Prevencion de Lavado de Dinero";
    }

    @Override
    public void notificar(CambioNivelRiesgo cambio) {
        System.out.println("   [AML] Monitoreo reforzado de la cuenta " + cambio.idCuenta()
                + " (" + cambio.nivelAnterior() + " -> " + cambio.nivelNuevo() + ")");
    }
}
