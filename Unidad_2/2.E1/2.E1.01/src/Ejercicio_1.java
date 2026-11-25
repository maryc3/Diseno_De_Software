import java.io.IOException;

public class Ejercicio_1 {

    public static void main(String[] args) {
        String rutaArchivo = "config.properties";

        try {
            System.out.println("=== 1. Carga inicial de la configuración ===");
            ConfigurationManager configuracion = ConfigurationManager.obtenerInstancia();
            configuracion.cargar(rutaArchivo);
            System.out.println("Archivo cargado: " + rutaArchivo);

            System.out.println();
            System.out.println("=== 2. Consulta de parámetros desde distintos componentes ===");
            ComponenteBaseDatos baseDatos = new ComponenteBaseDatos();
            ComponenteCorreo correo = new ComponenteCorreo();
            baseDatos.conectar();
            correo.enviar();

            System.out.println();
            System.out.println("=== 3. Verificación de instancia única ===");
            ConfigurationManager configuracionA = ConfigurationManager.obtenerInstancia();
            ConfigurationManager configuracionB = ConfigurationManager.obtenerInstancia();
            System.out.println("Referencia A: " + System.identityHashCode(configuracionA));
            System.out.println("Referencia B: " + System.identityHashCode(configuracionB));

            if (configuracionA == configuracionB) {
                System.out.println("¿Misma instancia? true");
            } else {
                System.out.println("¿Misma instancia? false");
            }

            System.out.println();
            System.out.println("=== 4. Segunda carga (se ignora) ===");
            configuracionA.cargar(rutaArchivo);
            System.out.println("La segunda llamada a cargar() no volvió a leer el archivo");

            System.out.println();
            System.out.println("=== 5. Clave que no existe ===");
            System.out.println("Sin valor por defecto: " + configuracion.obtener("clave.inexistente"));
            System.out.println("Con valor por defecto: " + configuracion.obtener("clave.inexistente", "N/A"));

            System.out.println();
            System.out.println("=== 6. Prueba con varios hilos ===");
            probarConHilos();

            System.out.println();
            System.out.println("=== 7. Constructor privado ===");
            System.out.println("No se puede hacer new ConfigurationManager() desde otra clase");
            System.out.println("Fin del programa");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error en la prueba con hilos: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    // Crea varios hilos y verifica que todos obtengan la misma instancia.
    private static void probarConHilos() throws InterruptedException {
        int cantidadHilos = 10;
        ConfigurationManager[] instancias = new ConfigurationManager[cantidadHilos];
        Thread[] hilos = new Thread[cantidadHilos];

        for (int i = 0; i < cantidadHilos; i++) {
            final int posicion = i;
            hilos[i] = new Thread(() -> {
                instancias[posicion] = ConfigurationManager.obtenerInstancia();
            });
            hilos[i].start();
        }

        for (int i = 0; i < cantidadHilos; i++) {
            hilos[i].join();
        }

        boolean todasIguales = true;
        for (int i = 1; i < cantidadHilos; i++) {
            if (instancias[i] != instancias[0]) {
                todasIguales = false;
            }
        }

        System.out.println("Se probaron " + cantidadHilos + " hilos");
        if (todasIguales) {
            System.out.println("Resultado: CORRECTO (todos obtuvieron la misma instancia)");
        } else {
            System.out.println("Resultado: ERROR (hay instancias distintas)");
        }
    }
}
