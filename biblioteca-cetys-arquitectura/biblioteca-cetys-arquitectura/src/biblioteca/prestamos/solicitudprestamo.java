package biblioteca.prestamos;

import biblioteca.catalogo.Libro;
import biblioteca.usuarios.Usuario;
import java.time.LocalDate;

/**
 * OBJETO INMUTABLE — SolicitudPrestamo
 *
 * Una vez construida con el Builder, ningún campo puede cambiar.
 * Solo se instancia desde SolicitudPrestamoBuilder.
 *
 * ¿Por qué inmutable?
 *  - Evita que el estado del préstamo cambie accidentalmente después
 *    de ser registrado, lo que podría causar inconsistencias en auditoría.
 *  - Thread-safe por diseño: múltiples hilos pueden leer el objeto sin locks.
 *  - Facilita el uso en colecciones y como clave en mapas.
 */
public final class SolicitudPrestamo {

    // ── Campos obligatorios ──────────────────────────────────────────────────
    private final Usuario     estudiante;
    private final Libro       libro;
    private final LocalDate   fechaDevolucion;

    // ── Campos opcionales ────────────────────────────────────────────────────
    private final String  notasEspeciales;
    private final boolean renovacionAutomatica;
    private final int     numRenovaciones;

    // Constructor PRIVADO — solo accesible desde el Builder
    private SolicitudPrestamo(Builder builder) {
        this.estudiante           = builder.estudiante;
        this.libro                = builder.libro;
        this.fechaDevolucion      = builder.fechaDevolucion;
        this.notasEspeciales      = builder.notasEspeciales;
        this.renovacionAutomatica = builder.renovacionAutomatica;
        this.numRenovaciones      = builder.numRenovaciones;
    }

    // ── Getters (sin setters — inmutabilidad garantizada) ────────────────────
    public Usuario   getEstudiante()           { return estudiante; }
    public Libro     getLibro()                { return libro; }
    public LocalDate getFechaDevolucion()      { return fechaDevolucion; }
    public String    getNotasEspeciales()      { return notasEspeciales; }
    public boolean   isRenovacionAutomatica()  { return renovacionAutomatica; }
    public int       getNumRenovaciones()      { return numRenovaciones; }

    @Override
    public String toString() {
        return String.format(
            "SolicitudPrestamo{\n  estudiante='%s'\n  libro='%s'\n" +
            "  fechaDevolucion=%s\n  notas='%s'\n  renovAuto=%b\n  numRenov=%d\n}",
            estudiante.getNombre(), libro.getTitulo(),
            fechaDevolucion, notasEspeciales,
            renovacionAutomatica, numRenovaciones);
    }

    // ════════════════════════════════════════════════════════════════════════
    // PATRÓN BUILDER — clase estática interna
    // ════════════════════════════════════════════════════════════════════════
    public static final class Builder {

        // Campos obligatorios
        private Usuario   estudiante;
        private Libro     libro;
        private LocalDate fechaDevolucion;

        // Campos opcionales con defaults
        private String  notasEspeciales      = null;
        private boolean renovacionAutomatica = false;
        private int     numRenovaciones      = 1;

        // ── Métodos encadenables para obligatorios ───────────────────────────
        public Builder estudiante(Usuario estudiante) {
            this.estudiante = estudiante;
            return this;
        }

        public Builder libro(Libro libro) {
            this.libro = libro;
            return this;
        }

        public Builder fechaDevolucion(LocalDate fecha) {
            this.fechaDevolucion = fecha;
            return this;
        }

        // ── Métodos encadenables para opcionales ─────────────────────────────
        public Builder notasEspeciales(String notas) {
            this.notasEspeciales = notas;
            return this;
        }

        public Builder renovacionAutomatica(boolean renovar) {
            this.renovacionAutomatica = renovar;
            return this;
        }

        public Builder numRenovaciones(int num) {
            this.numRenovaciones = num;
            return this;
        }

        /**
         * Valida campos obligatorios y construye el objeto inmutable.
         * @throws IllegalStateException si falta algún campo obligatorio
         */
        public SolicitudPrestamo construir() {
            // ── Validaciones ─────────────────────────────────────────────────
            if (estudiante == null)
                throw new IllegalStateException("Campo obligatorio faltante: estudiante");
            if (libro == null)
                throw new IllegalStateException("Campo obligatorio faltante: libro");
            if (fechaDevolucion == null)
                throw new IllegalStateException("Campo obligatorio faltante: fechaDevolucion");
            if (fechaDevolucion.isBefore(LocalDate.now()))
                throw new IllegalStateException("fechaDevolucion no puede ser en el pasado");
            if (numRenovaciones < 1)
                throw new IllegalStateException("numRenovaciones debe ser >= 1");

            return new SolicitudPrestamo(this);
        }
    }
}