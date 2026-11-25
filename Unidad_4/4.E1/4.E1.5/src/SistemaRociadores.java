package unidad4.E1;

public class SistemaRociadores implements ObservadorAlarma {

    @Override
    public void alDetectarHumo(AlertaHumo alerta) {
        System.out.println("   [Rociadores] Activando rociadores solo en " + alerta.ubicacion());
    }
}
