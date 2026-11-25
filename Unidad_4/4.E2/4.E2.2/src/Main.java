package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        MonitorRed monitor = new MonitorRed();
        monitor.suscribir(new IconoBarraHerramientas());
        monitor.suscribir(new BarraEstado());

        System.out.println("--- Se establece la conexion ---");
        monitor.cambiarEstado(EstadoRed.CONECTADO);

        System.out.println("--- Se informa CONECTADO otra vez (no hay cambio: nadie es notificado) ---");
        monitor.cambiarEstado(EstadoRed.CONECTADO);

        System.out.println("--- La senal se vuelve inestable ---");
        monitor.cambiarEstado(EstadoRed.INTERMITENTE);

        System.out.println("--- Se pierde la conexion ---");
        monitor.cambiarEstado(EstadoRed.DESCONECTADO);
    }
}
