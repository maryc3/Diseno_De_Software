package unidad4.E2;

public class IconoBarraHerramientas implements ObservadorRed {

    @Override
    public void alCambiarEstado(EstadoRed anterior, EstadoRed nuevo) {
        String icono = switch (nuevo) {
            case CONECTADO -> "[ (o) ]  senal completa";
            case DESCONECTADO -> "[ (x) ]  sin senal";
            case INTERMITENTE -> "[ (~) ]  senal inestable";
        };
        System.out.println("   [Icono barra de herramientas] " + icono);
    }
}
