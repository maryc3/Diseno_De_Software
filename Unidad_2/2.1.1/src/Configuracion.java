import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
    private static final Configuracion INSTANCIA = new Configuracion(

    private Configuracion() {

    });

    private final Properties propiedades;
    private Configuracion(
            private Configuracion() {
        propiedades = new Properties();
    }
    )

    public static Configuracion getInstancia() {
        return INSTANCIA;
    }

    private void cargarConfiguracion() {
        try (FileInputStream archivo = new FileInputStream("config.properties")) {
            propiedades.load(archivo);
        }

    }
}
