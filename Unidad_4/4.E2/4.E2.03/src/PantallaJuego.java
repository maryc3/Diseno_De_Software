package unidad4.E2;

/** Componente visual: muestra la puntuación en la pantalla principal. */
public class PantallaJuego implements ObservadorPuntuacion {

    @Override
    public void alCambiarPuntuacion(int puntuacionTotal, int puntosGanados, String motivo) {
        System.out.println("   [Pantalla] +" + puntosGanados + " por " + motivo + "  |  PUNTOS: " + puntuacionTotal);
    }
}
