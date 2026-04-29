package biblioteca.usuarios;

/**
 * EXTENSIÓN FUTURA — Tipo de usuario Posgrado.
 * Se agrega sin modificar ninguna clase existente (principio OCP).
 */
public class Posgrado implements Usuario {
    private final String id;
    private final String nombre;

    public Posgrado(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override public String getId()                  { return id; }
    @Override public String getNombre()              { return nombre; }
    @Override public String getRol()                 { return "POSGRADO"; }
    @Override public int getLimitePrestamos()        { return 7; }
    @Override public int getDiasPrestamoMaximo()     { return 21; }
    @Override public boolean puedeReservarSala()     { return true; }

    @Override
    public void mostrarInfo() {
        System.out.printf("[Posgrado] ID: %s | Nombre: %s | Límite: %d libros | Días: %d%n",
                id, nombre, getLimitePrestamos(), getDiasPrestamoMaximo());
    }
}