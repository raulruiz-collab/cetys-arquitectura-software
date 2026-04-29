package biblioteca.prestamos;

import biblioteca.catalogo.Libro;
import biblioteca.usuarios.FabricaDeUsuarios;
import biblioteca.usuarios.Usuario;
import java.time.LocalDate;

/**
 * Ejemplos de uso del SolicitudPrestamoBuilder
 * con distintas combinaciones de atributos opcionales.
 */
public class EjemplosBuilder {

    public static void main(String[] args) {

        // Datos de prueba
        Usuario ana   = FabricaDeUsuarios.crear("ESTUDIANTE", "EST-001", "Ana López");
        Usuario carlos = FabricaDeUsuarios.crear("POSGRADO",  "POS-001", "Dr. Carlos Ruiz");
        Libro libro1  = new Libro("978-0-07-301775-3", "Ingeniería de Software", "Pressman", true);
        Libro libro2  = new Libro("978-0-13-468599-1", "Clean Code", "Robert C. Martin", true);

        // ── Ejemplo 1: Solo campos obligatorios ─────────────────────────────
        SolicitudPrestamo solicitud1 = new SolicitudPrestamo.Builder()
                .estudiante(ana)
                .libro(libro1)
                .fechaDevolucion(LocalDate.now().plusDays(14))
                .construir();

        System.out.println("=== Ejemplo 1: Solo obligatorios ===");
        System.out.println(solicitud1);

        // ── Ejemplo 2: Con nota especial y renovación automática ────────────
        SolicitudPrestamo solicitud2 = new SolicitudPrestamo.Builder()
                .estudiante(ana)
                .libro(libro2)
                .fechaDevolucion(LocalDate.now().plusDays(14))
                .notasEspeciales("Tesis doctoral — prioridad alta")
                .renovacionAutomatica(true)
                .construir();

        System.out.println("\n=== Ejemplo 2: Con nota y renovación automática ===");
        System.out.println(solicitud2);

        // ── Ejemplo 3: Todos los campos ──────────────────────────────────────
        SolicitudPrestamo solicitud3 = new SolicitudPrestamo.Builder()
                .estudiante(carlos)
                .libro(libro1)
                .fechaDevolucion(LocalDate.now().plusDays(21))
                .notasEspeciales("Reservado para seminario de posgrado")
                .renovacionAutomatica(true)
                .numRenovaciones(3)
                .construir();

        System.out.println("\n=== Ejemplo 3: Todos los atributos ===");
        System.out.println(solicitud3);

        // ── Ejemplo 4: Demostrar validación de campos obligatorios ───────────
        System.out.println("\n=== Ejemplo 4: Error por campo obligatorio faltante ===");
        try {
            SolicitudPrestamo invalida = new SolicitudPrestamo.Builder()
                    .estudiante(ana)
                    // libro faltante a propósito
                    .fechaDevolucion(LocalDate.now().plusDays(7))
                    .construir();
        } catch (IllegalStateException e) {
            System.out.println("Error capturado correctamente: " + e.getMessage());
        }
    }
}