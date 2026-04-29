package biblioteca.usuarios;

/**
 * PATRÓN FACTORY — FabricaDeUsuarios
 *
 * El código cliente solo conoce la interfaz `Usuario` y el tipo como String.
 * Nunca instancia directamente Estudiante, Bibliotecario, Admin, etc.
 *
 * Principio SOLID aplicado: OCP (Open/Closed Principle)
 * Para agregar un nuevo tipo (ej. Posgrado, Externo, Profesor) basta con:
 *   1. Crear la nueva clase que implemente Usuario.
 *   2. Agregar un case en el switch de esta fábrica.
 * No se modifica ninguna clase existente.
 */
public class FabricaDeUsuarios {

    /**
     * Crea y retorna el usuario correspondiente al tipo indicado.
     *
     * @param tipo    Tipo de usuario: "ESTUDIANTE", "BIBLIOTECARIO", "ADMIN", "POSGRADO"
     * @param id      Identificador único del usuario
     * @param nombre  Nombre completo del usuario
     * @return        Instancia concreta de Usuario
     */
    public static Usuario crear(String tipo, String id, String nombre) {
        return switch (tipo.toUpperCase()) {
            case "ESTUDIANTE"    -> new Estudiante(id, nombre);
            case "BIBLIOTECARIO" -> new Bibliotecario(id, nombre);
            case "ADMIN"         -> new Admin(id, nombre);
            case "POSGRADO"      -> new Posgrado(id, nombre);   // ← extensión sin modificar anteriores
            default -> throw new IllegalArgumentException(
                    "Tipo de usuario desconocido: " + tipo);
        };
    }

    // ── Demostración ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Usuario u1 = FabricaDeUsuarios.crear("ESTUDIANTE",    "EST-001", "Ana López");
        Usuario u2 = FabricaDeUsuarios.crear("BIBLIOTECARIO", "BIB-001", "Carlos Ruiz");
        Usuario u3 = FabricaDeUsuarios.crear("ADMIN",         "ADM-001", "Dirección TI");
        Usuario u4 = FabricaDeUsuarios.crear("POSGRADO",      "POS-001", "Dr. Martínez");

        u1.mostrarInfo();
        u2.mostrarInfo();
        u3.mostrarInfo();
        u4.mostrarInfo();
    }
}