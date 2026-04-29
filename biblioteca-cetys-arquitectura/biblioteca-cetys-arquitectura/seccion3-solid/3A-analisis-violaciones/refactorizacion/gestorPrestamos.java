package biblioteca.refactorizacion;

import biblioteca.auditoria.AuditoriaLogger;

/**
 * DESPUÉS DE REFACTORIZACIÓN — SRP aplicado.
 * Responsabilidad única: gestionar préstamos y devoluciones.
 */
public class GestorPrestamos {

    private final RepositorioPrestamosInterface repo;
    private final AuditoriaLogger auditoria;

    public GestorPrestamos(RepositorioPrestamosInterface repo) {
        this.repo = repo;
        this.auditoria = AuditoriaLogger.getInstance();
    }

    public void prestarLibro(String isbn, String usuarioId) {
        // lógica de negocio de préstamo
        auditoria.registrar("PRESTAMO_CREADO isbn=" + isbn, usuarioId);
    }

    public void devolverLibro(String prestamoId, String usuarioId) {
        // lógica de devolución
        auditoria.registrar("DEVOLUCION prestamoId=" + prestamoId, usuarioId);
    }
}