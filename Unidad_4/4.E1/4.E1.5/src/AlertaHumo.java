package unidad4.E1;

/** Señal de emergencia con la ubicación exacta del sensor que detectó humo. */
public record AlertaHumo(String idSensor, int piso, String zona) {

    public String ubicacion() {
        return "piso " + piso + ", " + zona;
    }
}
