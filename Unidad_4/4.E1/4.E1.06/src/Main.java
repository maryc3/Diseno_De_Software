package unidad4.E1;

public class Main {

    public static void main(String[] args) {
        ActivoFinanciero acme = new ActivoFinanciero("ACME", 100.00);

        acme.suscribir(new PanelGrafico());
        acme.suscribir(new PanelResumen());
        acme.suscribir(new AlertaVolatilidad(5.0, acme));

        double[] precios = {102.00, 99.00, 110.00, 108.00};
        for (double precio : precios) {
            System.out.println("--- Nuevo precio: " + precio + " ---");
            acme.actualizarPrecio(precio);
        }
    }
}
