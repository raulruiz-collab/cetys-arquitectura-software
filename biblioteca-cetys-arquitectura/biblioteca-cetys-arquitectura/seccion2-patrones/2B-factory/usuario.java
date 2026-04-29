package biblioteca.usuarios;

/**
 * INTERFAZ COMÚN para todos los tipos de usuario del sistema.
 * Define el contrato que deben cumplir todas las implementaciones.
 */
public interface Usuario {
    String getId();
    String getNombre();
    String getRol();
    int getLimitePrestamos();
    int getDiasPrestamoMaximo();
    boolean puedeReservarSala();
    void mostrarInfo();
}