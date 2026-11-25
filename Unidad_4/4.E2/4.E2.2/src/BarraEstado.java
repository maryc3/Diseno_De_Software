package unidad4.E2;

public class BarraEstado implements ObservadorRed {

    @Override
    public void alCambiarEstado(EstadoRed anterior, EstadoRed nuevo) {
        System.out.println("   [Barra de estado] Red: " + nuevo + " (antes: " + anterior + ")");
    }
}
