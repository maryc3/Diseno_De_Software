package unidad4.E1;

public class GeneradorReportesRegulatorios implements ObservadorRegulatorio {

    @Override
    public String nombre() {
        return "Reportes Regulatorios";
    }

    @Override
    public void procesar(CambioRiesgo cambio) {
        System.out.println("   [Reportes] Reporte obligatorio generado para " + cambio.idCliente());
    }
}
