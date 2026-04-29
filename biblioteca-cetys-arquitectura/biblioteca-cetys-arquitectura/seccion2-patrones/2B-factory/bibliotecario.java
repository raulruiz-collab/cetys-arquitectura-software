package biblioteca.usuarios;

public class Bibliotecario implements Usuario {
    private final String id;
    private final String nombre;

    public Bibliotecario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override public String getId()                  { return id; }
    @Override public String getNombre()              { return nombre; }
    @Override public String getRol()                 { return "BIBLIOTECARIO"; }
    @Override public int getLimitePrestamos()        { return 10; }
    @Override public int getDiasPrestamoMaximo()     { return 30; }
    @Override public boolean puedeReservarSala()     { return true; }

    @Override
    public void mostrarInfo() {
        System.out.printf("[Bibliotecario] ID: %s | Nombre: %s | Límite: %d libros | Días: %d%n",
                id, nombre, getLimitePrestamos(), getDiasPrestamoMaximo());
    }
}