package unidad4.E2;

public class AnalisisLegal implements EntidadReceptora {

    @Override
    public String nombre() {
        return "Analisis Legal";
    }

    @Override
    public void notificar(CambioNivelRiesgo cambio) {
        System.out.println("   [Legal] Analisis contractual abierto para " + cambio.idCuenta());
    }
}
