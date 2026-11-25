package unidad4.E1;

/** Dato de negocio simple. No sabe nada de consola, archivos ni auditoría. */
public class Empleado {

    private final int id;
    private String nombre;
    private String cargo;
    private double salario;

    public Empleado(int id, String nombre, String cargo, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }

    // Visibilidad de paquete: solo el sistema central puede modificar empleados.
    void setCargo(String cargo) { this.cargo = cargo; }
    void setSalario(double salario) { this.salario = salario; }
}
