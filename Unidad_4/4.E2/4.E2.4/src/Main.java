package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        Producto auriculares = new Producto("Auriculares X200", 100.00);
        Producto teclado = new Producto("Teclado mecanico", 80.00);

        NotificadorCorreo correo = new NotificadorCorreo("ana@ejemplo.com");
        NotificadorPanelWeb panel = new NotificadorPanelWeb();

        // Auriculares: correo + panel. Teclado: solo panel.
        auriculares.vincular(correo);
        auriculares.vincular(panel);
        teclado.vincular(panel);

        System.out.println("--- El precio SUBE: no se notifica ---");
        auriculares.actualizarPrecio(110.00);

        System.out.println("--- Rebaja pequena ---");
        auriculares.actualizarPrecio(104.50);

        System.out.println("--- Rebaja grande ---");
        auriculares.actualizarPrecio(75.00);

        System.out.println("--- Otro articulo: solo esta vinculado el panel web ---");
        teclado.actualizarPrecio(76.00);
    }
}
