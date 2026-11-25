package unidad4.E1.Ejercicio8;

import unidad4.E1.Ejercicio8antes.ProductoAcoplado;

public class Main {

    public static void main(String[] args) {
        System.out.println("=========== ANTES (acoplado) ===========");
        ProductoAcoplado viejo = new ProductoAcoplado("Teclado", 20);
        viejo.vender(2);

        System.out.println();
        System.out.println("=========== DESPUES (Observer) ===========");
        // Este metodo main es el unico lugar que conoce las clases concretas ("raiz de composicion").
        Producto teclado = new Producto("Teclado", 20);
        teclado.suscribir(new ModuloFacturacion());
        ModuloEstadisticas estadisticas = new ModuloEstadisticas();
        teclado.suscribir(estadisticas);

        teclado.vender(2);
        teclado.vender(3);

        System.out.println("--- Se agrega un modulo nuevo SIN tocar la clase Producto ---");
        teclado.suscribir(cambio ->
                System.out.println("   [Alertas (lambda)] Cambio detectado en " + cambio.producto()));
        teclado.reponer(10);
    }
}
