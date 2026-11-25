package unidad4.E2;

/** Oyente implementado como clase tradicional. */
public class RegistroClics implements OyenteClic {

    @Override
    public void alHacerClic(EventoClic evento) {
        System.out.println("   [RegistroClics] Clic numero " + evento.numeroClic() + " en " + evento.idBoton());
    }
}
