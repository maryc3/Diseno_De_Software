package unidad4.E2;

/** Respuesta eléctrica: cuanta más luz entra, menos intensidad necesitan las lámparas (mínimo 10%). */
public class LucesInteriores implements ObservadorLuz {

    @Override
    public void alCambiarLuz(LecturaLuz lectura) {
        int intensidad = Math.max(10, 100 - lectura.lux() / 1000);
        System.out.println("   [Luces " + lectura.zona() + "] intensidad al " + intensidad + "%");
    }
}
