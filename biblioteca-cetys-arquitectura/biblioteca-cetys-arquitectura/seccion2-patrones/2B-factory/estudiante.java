package biblioteca.usuarios;

public class Estudiante implements Usuario {
    private final String id;
    private final String nombre;

    public Estudiante(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override public String getId()                  { return id; }
    @Override public String getNombre()              { return nombre; }
    @Override public String getRol()                 { return "ESTUDIANTE"; }
    @Override public int getLimitePrestamos()        { return 3; }
    @Override public int getDiasPrestamoMaximo()     { return 14; }
    @Override public boolean puedeReservarSala()     { return true; }

    @Override
    public void mostrarInfo() {
        System.out.printf("[Estudiante] ID: %s | Nombre: %s | Límite: %d libros | Días: %d%n",
                id, nombre, getLimitePrestamos(), getDiasPrestamoMaximo());
    }
}