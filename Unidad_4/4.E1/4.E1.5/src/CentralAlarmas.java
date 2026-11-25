package unidad4.E1;

public class CentralAlarmas implements ObservadorAlarma {

    @Override
    public void alDetectarHumo(AlertaHumo alerta) {
        System.out.println("   [Central de alarmas] Sirena activada. Ubicacion: " + alerta.ubicacion());
    }
}
