package unidad4.E1.Ejercicio2;

public class Main {

    public static void main(String[] args) {
        MotorReproduccion motor = new MotorReproduccion();

        motor.suscribir(new BarraInferiorControl());
        motor.suscribir(new VentanaMiniatura());
        motor.suscribir(new WidgetPantallaPrincipal());

        System.out.println("--- Se elige una cancion ---");
        motor.reproducir("Bohemian Rhapsody");

        System.out.println("--- Pausa ---");
        motor.cambiarEstado(EstadoReproduccion.PAUSADO);

        System.out.println("--- Cambio de cancion ---");
        motor.reproducir("Hotel California");

        System.out.println("--- Detener ---");
        motor.cambiarEstado(EstadoReproduccion.DETENIDO);
    }
}
