package unidad4.E2;

/** Sistema de récords: mantiene el mejor puntaje histórico. */
public class RegistroRecords implements ObservadorPuntuacion {

    private int record;

    public RegistroRecords(int recordInicial) {
        this.record = recordInicial;
    }

    @Override
    public void alCambiarPuntuacion(int puntuacionTotal, int puntosGanados, String motivo) {
        if (puntuacionTotal > record) {
            record = puntuacionTotal;
            System.out.println("   [Records] NUEVO RECORD: " + record + " puntos");
        }
    }

    public int getRecord() {
        return record;
    }
}
