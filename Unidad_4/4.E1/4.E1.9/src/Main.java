package unidad4.E1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BusEventos bus = new BusEventos();

        // Notificaciones se registra PRIMERO en MEMORIA: si falla, los siguientes igual deben ejecutarse
        MicroservicioNotificaciones notificaciones = new MicroservicioNotificaciones();
        bus.suscribir(TipoAlerta.CPU_AL_90, new MicroservicioEscalado());
        bus.suscribir(TipoAlerta.CPU_AL_90, notificaciones);
        bus.suscribir(TipoAlerta.MEMORIA_INSUFICIENTE, notificaciones);
        bus.suscribir(TipoAlerta.MEMORIA_INSUFICIENTE, new MicroservicioCache());
        bus.suscribir(TipoAlerta.FALLO_DE_DISCO, new MicroservicioRespaldo());
        bus.suscribir(TipoAlerta.FALLO_DE_DISCO, notificaciones);

        System.out.println("=== 1) Despacho selectivo por tipo ===");
        bus.publicar(new EventoSistema("nodo-A", TipoAlerta.CPU_AL_90, Severidad.ADVERTENCIA, "CPU sostenida al 92%"));
        bus.publicar(new EventoSistema("nodo-B", TipoAlerta.FALLO_DE_DISCO, Severidad.CRITICA, "Disco /dev/sda sin respuesta"));

        System.out.println("=== 2) Un observador falla y los demas igual reciben el evento ===");
        bus.publicar(new EventoSistema("nodo-C", TipoAlerta.MEMORIA_INSUFICIENTE, Severidad.CRITICA, "Memoria libre < 2%"));

        System.out.println("=== 3) Concurrencia: 200 eventos publicados desde hilos del bus ===");
        // Bus aparte con un unico observador silencioso, para que la salida no se llene de mensajes.
        BusEventos busConcurrente = new BusEventos();
        ContadorEventos contador = new ContadorEventos();
        busConcurrente.suscribir(TipoAlerta.CPU_AL_90, contador);
        for (int i = 0; i < 200; i++) {
            busConcurrente.publicarAsincrono(new EventoSistema("nodo-" + i, TipoAlerta.CPU_AL_90, Severidad.INFO, "carga " + i));
        }
        busConcurrente.cerrar();
        bus.cerrar();
        System.out.println("Eventos recibidos por el contador: " + contador.getTotal() + " (esperado: 200)");
    }
}
