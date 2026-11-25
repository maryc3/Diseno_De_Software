// Componente que usa la configuración para conectarse a la base de datos.
public class ComponenteBaseDatos {

    public void conectar() {
        ConfigurationManager configuracion = ConfigurationManager.obtenerInstancia();
        String url = configuracion.obtener("db.url");
        String usuario = configuracion.obtener("db.user");

        System.out.println("[BaseDatos] Conectando a " + url + " con el usuario " + usuario);
    }
}
