import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/*
 * Clase Singleton: solo puede existir un objeto de esta clase
 * y se accede a él con obtenerInstancia().
 */
public class ConfigurationManager {

    // Única instancia de la clase (empieza en null).
    private static ConfigurationManager instancia;

    private Properties propiedades;
    private boolean cargado;

    // Constructor privado: impide crear objetos con "new" desde afuera.
    private ConfigurationManager() {
        propiedades = new Properties();
        cargado = false;
    }

    // Método estático que devuelve siempre el mismo objeto.
    public static synchronized ConfigurationManager obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConfigurationManager();
        }
        return instancia;
    }

    // Carga el archivo una sola vez. Si ya estaba cargado, no hace nada.
    public synchronized void cargar(String rutaArchivo) throws IOException {
        if (cargado) {
            return;
        }

        File archivo = new File(rutaArchivo);

        // Si el archivo no existe, se crea con valores por defecto.
        if (!archivo.exists()) {
            System.out.println("No se encontró " + archivo.getAbsolutePath()
                    + ", se crea con valores por defecto");
            crearArchivoPorDefecto(archivo);
        }

        FileInputStream lector = new FileInputStream(archivo);
        try {
            propiedades.load(lector);
        } finally {
            lector.close();
        }
        cargado = true;
    }

    // Devuelve el valor de una clave (o null si no existe).
    public String obtener(String clave) {
        verificarCarga();
        return propiedades.getProperty(clave);
    }

    // Devuelve el valor de una clave (o el valor por defecto si no existe).
    public String obtener(String clave, String valorPorDefecto) {
        verificarCarga();
        return propiedades.getProperty(clave, valorPorDefecto);
    }

    // Si se consulta antes de cargar, avisa con un error claro.
    private void verificarCarga() {
        if (!cargado) {
            throw new IllegalStateException("Primero hay que llamar a cargar()");
        }
    }

    private void crearArchivoPorDefecto(File archivo) throws IOException {
        PrintWriter escritor = new PrintWriter(archivo, "UTF-8");
        try {
            escritor.println("db.url=jdbc:mysql://localhost:3306/miapp");
            escritor.println("db.user=admin");
            escritor.println("mail.host=smtp.ejemplo.com");
            escritor.println("app.nombre=MiAplicacion");
        } finally {
            escritor.close();
        }
    }
}
