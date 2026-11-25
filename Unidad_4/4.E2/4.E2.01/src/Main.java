package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        Boton boton = new Boton("btnGuardar");
        Ventana ventana = new Ventana("Editor de texto");

        // Se guardan en variables para poder quitarlos despues
        OyenteClic oyenteVentana = ventana::alClicGuardar;               // referencia a metodo
        OyenteClic oyenteLambda = e -> System.out.println("   [Lambda] Se detecto el clic #" + e.numeroClic());
        OyenteClic oyenteClase = new RegistroClics();                    // clase tradicional

        boton.registrar(oyenteVentana);
        boton.registrar(oyenteLambda);
        boton.registrar(oyenteClase);

        System.out.println("--- Clic 1 (3 oyentes) ---");
        boton.simularClic();

        System.out.println("--- Clic 2 (3 oyentes) ---");
        boton.simularClic();

        System.out.println("--- Se quita el oyente lambda ---");
        boton.quitar(oyenteLambda);

        System.out.println("--- Clic 3 (2 oyentes) ---");
        boton.simularClic();
    }
}
