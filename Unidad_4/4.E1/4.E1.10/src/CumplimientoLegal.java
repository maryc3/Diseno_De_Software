package unidad4.E1;

public class CumplimientoLegal implements ObservadorRegulatorio {

    @Override
    public String nombre() {
        return "Cumplimiento Legal";
    }

    @Override
    public void procesar(CambioRiesgo cambio) {
        System.out.println("   [Legal] Revisando legajo de " + cambio.idCliente()
                + " (" + cambio.nivelAnterior() + " -> " + cambio.nivelNuevo() + ")");
    }
}
