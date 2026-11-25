package unidad4.E2;

/** Respuesta mecánica: baja las persianas de la zona según la intensidad solar. */
public class PersianasMotorizadas implements ObservadorLuz {

    @Override
    public void alCambiarLuz(LecturaLuz lectura) {
        int cierre;
        if (lectura.lux() >= 60000) {
            cierre = 100;
        } else if (lectura.lux() >= 30000) {
            cierre = 50;
        } else {
            cierre = 0;
        }
        System.out.println("   [Persianas " + lectura.zona() + "] cierre al " + cierre + "%");
    }
}
