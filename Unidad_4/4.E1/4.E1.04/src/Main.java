package unidad4.E1;

public class Main {

    public static void main(String[] args) {
        DepartamentoCompras compras = new DepartamentoCompras();
        SistemaMarketing marketing = new SistemaMarketing();

        // Notebook: le interesa a compras Y a marketing
        Producto notebook = new Producto("Notebook", 10, 5);
        notebook.suscribir(compras);
        notebook.suscribir(marketing);

        // Mouse: solo le interesa a compras
        Producto mouse = new Producto("Mouse", 8, 3);
        mouse.suscribir(compras);

        System.out.println("=== Notebook (umbral 5) ===");
        notebook.vender(3);   // 7: no notifica
        notebook.vender(3);   // 4: notifica
        notebook.vender(4);   // 0: notifica (agotado)
        try {
            notebook.vender(1);
        } catch (IllegalStateException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        System.out.println("=== Mouse (umbral 3, solo Compras) ===");
        mouse.vender(6);      // 2: notifica solo a compras
    }
}
