package unidad4.E2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CuentaCorporativa cuenta = new CuentaCorporativa("CTA-CORP-77", NivelRiesgoCuenta.BAJO);

        cuenta.registrar(new PrevencionLavadoDinero());
        cuenta.registrar(new AnalisisLegal());
        cuenta.registrar(new OrganismoRegulatorioInternacional());

        System.out.println("=== Cambio 1: BAJO -> ALTO (el organismo internacional falla por timeout) ===");
        mostrar(cuenta.cambiarNivel(NivelRiesgoCuenta.ALTO));

        System.out.println("=== Cambio 2: ALTO -> MEDIO (todo funciona) ===");
        mostrar(cuenta.cambiarNivel(NivelRiesgoCuenta.MEDIO));

        System.out.println("=== Auditoria inmutable ===");
        List<RegistroAuditoria> auditoria = cuenta.getAuditoria();
        System.out.println("Registros de auditoria: " + auditoria.size());
        try {
            auditoria.get(0).resultados().add(new ResultadoNotificacion("Intruso", true, "manipulado"));
        } catch (UnsupportedOperationException e) {
            System.out.println("Intento de alterar un registro -> UnsupportedOperationException (correcto)");
        }

        System.out.println("=== Seguridad multihilo: 8 hilos registran entidades a la vez ===");
        ExecutorService pool = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            final String nombre = "Entidad-extra-" + i;
            pool.submit(() -> cuenta.registrar(crearEntidad(nombre)));
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Entidades registradas: " + cuenta.cantidadEntidades() + " (esperado: 11)");
    }

    private static void mostrar(RegistroAuditoria registro) {
        System.out.println("Notificadas con exito: " + registro.entidadesExitosas());
        System.out.println("Con incidencias:       " + registro.entidadesConIncidencias());
    }

    private static EntidadReceptora crearEntidad(String nombre) {
        return new EntidadReceptora() {
            @Override
            public String nombre() {
                return nombre;
            }

            @Override
            public void notificar(CambioNivelRiesgo cambio) {
                // sin accion: solo sirve para la prueba de registro concurrente
            }
        };
    }
}
