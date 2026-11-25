package unidad4.E1;

/**
 * Dispositivo NUEVO, agregado después. Se incorpora sin tocar ni una línea de SensorHumo
 * (principio abierto/cerrado).
 */
public class ControlAscensores implements ObservadorAlarma {

    @Override
    public void alDetectarHumo(AlertaHumo alerta) {
        System.out.println("   [Ascensores] Bloqueados y enviados a planta baja (alerta en piso " + alerta.piso() + ")");
    }
}
