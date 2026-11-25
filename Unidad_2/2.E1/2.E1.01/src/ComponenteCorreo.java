// Componente que usa la configuración para enviar correos.
public class ComponenteCorreo {

    public void enviar() {
        ConfigurationManager configuracion = ConfigurationManager.obtenerInstancia();
        String servidor = configuracion.obtener("mail.host", "localhost");

        System.out.println("[Correo] Enviando mensaje por el servidor " + servidor);
    }
}
