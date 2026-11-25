package unidad4.E1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        PerfilRiesgoCliente perfil = new PerfilRiesgoCliente("CLI-1001", NivelRiesgo.BAJO);

        perfil.registrar(new CumplimientoLegal());
        perfil.registrar(new PrevencionLavadoDinero());
        perfil.registrar(new GeneradorReportesRegulatorios());

        System.out.println("=== Cambio 1: BAJO -> ALTO (todos los sistemas funcionan) ===");
        mostrar(perfil.cambiarNivel(NivelRiesgo.ALTO));

        System.out.println("=== Cambio 2: ALTO -> CRITICO (AML falla, los demas siguen) ===");
        mostrar(perfil.cambiarNivel(NivelRiesgo.CRITICO));

        System.out.println("=== Historial inmutable ===");
        List<InformeNotificacion> historial = perfil.getHistorial();
        System.out.println("Cantidad de informes: " + historial.size());
        try {
            historial.clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("Intento de modificar el historial -> UnsupportedOperationException (correcto)");
        }

        System.out.println("=== Seguridad multihilo: 8 hilos registran observadores a la vez ===");
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            final String nombre = "Observador-extra-" + i;
            Thread hilo = new Thread(() -> perfil.registrar(crearObservador(nombre)));
            hilos.add(hilo);
            hilo.start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }
        System.out.println("Observadores registrados: " + perfil.cantidadObservadores() + " (esperado: 11)");
    }

    private static void mostrar(InformeNotificacion informe) {
        System.out.println("Informe del cambio " + informe.cambio().nivelAnterior() + " -> " + informe.cambio().nivelNuevo());
        for (RegistroNotificacion r : informe.registros()) {
            System.out.println("   - " + r.observador() + ": " + (r.exitoso() ? "OK" : "FALLO") + " (" + r.detalle() + ")");
        }
    }

    private static ObservadorRegulatorio crearObservador(String nombre) {
        return new ObservadorRegulatorio() {
            @Override
            public String nombre() {
                return nombre;
            }

            @Override
            public void procesar(CambioRiesgo cambio) {
                // sin accion: solo sirve para la prueba de registro concurrente
            }
        };
    }
}
