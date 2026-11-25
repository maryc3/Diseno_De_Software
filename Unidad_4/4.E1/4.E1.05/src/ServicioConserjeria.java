package unidad4.E1;

public class ServicioConserjeria implements ObservadorAlarma {

    @Override
    public void alDetectarHumo(AlertaHumo alerta) {
        System.out.println("   [Conserjeria] Aviso al personal: revisar " + alerta.ubicacion());
    }
}
