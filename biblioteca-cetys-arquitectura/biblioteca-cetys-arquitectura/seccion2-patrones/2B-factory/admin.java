package biblioteca.usuarios;

public class Admin implements Usuario {
    private final String id;
    private final String nombre;

    public Admin(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override public String getId()                  { return id; }
    @Override public String getNombre()              { return nombre; }
    @Override public String getRol()                 { return "ADMIN"; }
    @Override public int getLimitePrestamos()        { return 999; }
    @Override public int getDiasPrestamoMaximo()     { return 365; }
    @Override public boolean puedeReservarSala()     { return true; }

    @Override
    public void mostrarInfo() {
        System.out.printf("[Admin] ID: %s | Nombre: %s | Acceso total%n", id, nombre);
    }
}