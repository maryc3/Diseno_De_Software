package unidad4.E1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path bitacora = Path.of("bitacora-auditoria-ej03.log");
        Files.deleteIfExists(bitacora); // empezamos con una bitacora limpia

        SistemaEmpleados sistema = new SistemaEmpleados();
        AuditorConsola consola = new AuditorConsola();
        AuditorArchivo archivo = new AuditorArchivo(bitacora);

        sistema.suscribir(consola);
        sistema.suscribir(archivo);

        System.out.println("--- Con consola y archivo suscritos ---");
        sistema.alta(new Empleado(1, "Laura Gomez", "Analista", 1500.00));
        sistema.alta(new Empleado(2, "Martin Perez", "Programador", 1800.00));
        sistema.modificarSalario(2, 2100.00);

        System.out.println("--- Se desuscribe la consola en plena ejecucion (solo queda el archivo) ---");
        sistema.desuscribir(consola);
        sistema.modificarCargo(1, "Lider de equipo");
        sistema.baja(2);

        System.out.println("--- Contenido de la bitacora (" + bitacora + ") ---");
        Files.readAllLines(bitacora).forEach(linea -> System.out.println("  " + linea));
    }
}
