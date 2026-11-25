package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        MercadoDivisas mercado = new MercadoDivisas();

        mercado.suscribir(new PanelTendencia());
        mercado.suscribir(new TablaConversion());
        mercado.suscribir(new AlarmaLimite("USD", 1250.00, mercado));

        // Valores de ejemplo inventados para la demostracion
        System.out.println("--- Primera cotizacion de cada divisa (variacion 0) ---");
        mercado.actualizarCotizacion("USD", 1200.00);
        mercado.actualizarCotizacion("EUR", 1300.00);

        System.out.println("--- USD sube ---");
        mercado.actualizarCotizacion("USD", 1230.00);

        System.out.println("--- EUR baja ---");
        mercado.actualizarCotizacion("EUR", 1280.00);

        System.out.println("--- USD supera el limite: la alarma se dispara y se desvincula sola ---");
        mercado.actualizarCotizacion("USD", 1260.00);

        System.out.println("--- USD vuelve a subir: la alarma ya no existe ---");
        mercado.actualizarCotizacion("USD", 1290.00);
    }
}
