package unidad4.E2;

/**
 * Dispositivo NUEVO agregado después. Se incorpora sin modificar el código fuente del sensor.
 */
public class CortinasTermicas implements ObservadorLuz {

    @Override
    public void alCambiarLuz(LecturaLuz lectura) {
        if (lectura.lux() >= 60000) {
            System.out.println("   [Cortinas termicas " + lectura.zona() + "] desplegadas para aislar el calor");
        } else {
            System.out.println("   [Cortinas termicas " + lectura.zona() + "] recogidas");
        }
    }
}
