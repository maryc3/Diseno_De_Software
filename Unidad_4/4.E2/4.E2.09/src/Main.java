package unidad4.E2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BusEventos bus = new BusEventos(4);

        // La guardia se registra primero en cada tipo: si falla, los siguientes igual deben ejecutarse
        ServicioGuardia guardia = new ServicioGuardia();
        bus.suscribir(TipoAlerta.CPU_AL_90, guardia);
        bus.suscribir(TipoAlerta.CPU_AL_90, new ServicioAutoescalado());
        bus.suscribir(TipoAlerta.MEMORIA_INSUFICIENTE, guardia);
        bus.suscribir(TipoAlerta.MEMORIA_INSUFICIENTE, new ServicioLimpiezaMemoria());
        bus.suscribir(TipoAlerta.FALLO_DE_DISCO, new ServicioRespaldoDisco());

        System.out.println("=== 1) Despacho selectivo por tipo ===");
        bus.publicar(new EventoSistema("nodo-A", TipoAlerta.CPU_AL_90, 3, "CPU sostenida al 92%"));
        bus.publicar(new EventoSistema("nodo-B", TipoAlerta.FALLO_DE_DISCO, 4, "Disco /dev/sdb con errores"));

        System.out.println("=== 2) Un observador falla (severidad 5) y los demas igual reciben el evento ===");
        bus.publicar(new EventoSistema("nodo-C", TipoAlerta.MEMORIA_INSUFICIENTE, 5, "Memoria libre < 2%"));

        System.out.println("=== 3) Fallos registrados por el bus ===");
        for (FalloEntrega fallo : bus.getFallos()) {
            System.out.println("   - " + fallo.observador() + " no pudo procesar " + fallo.evento().tipo()
                    + " de " + fallo.evento().nodoOrigen() + ": " + fallo.error());
        }

        System.out.println("=== 4) Concurrencia: 300 eventos asincronicos con CompletableFuture ===");
        // Bus aparte con un contador silencioso para no llenar la consola
        BusEventos busConcurrente = new BusEventos(8);
        ContadorEventos contador = new ContadorEventos();
        busConcurrente.suscribir(TipoAlerta.FALLO_DE_DISCO, contador);

        List<CompletableFuture<Void>> pendientes = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            pendientes.add(busConcurrente.publicarAsincrono(
                    new EventoSistema("nodo-" + i, TipoAlerta.FALLO_DE_DISCO, 1, "evento " + i)));
        }
        pendientes.forEach(CompletableFuture::join); // espera a que terminen todos
        System.out.println("Eventos recibidos por el contador: " + contador.getTotal() + " (esperado: 300)");

        busConcurrente.cerrar();
        bus.cerrar();
    }
}
