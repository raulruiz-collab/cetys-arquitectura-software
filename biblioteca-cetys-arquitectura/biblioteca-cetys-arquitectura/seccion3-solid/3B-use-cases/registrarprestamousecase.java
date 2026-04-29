package biblioteca.usecases;

import biblioteca.auditoria.AuditoriaLogger;
import biblioteca.catalogo.CatalogoBiblioteca;
import biblioteca.domain.model.Prestamo;
import biblioteca.domain.ports.RepositorioPrestamos;
import biblioteca.prestamos.SolicitudPrestamo;

import java.time.LocalDate;
import java.util.UUID;

/**
 * CASO DE USO — RegistrarPrestamo (Clean Architecture)
 *
 * Esta clase:
 *  ✓ No conoce ningún framework (Spring, Hibernate, etc.)
 *  ✓ No conoce la BD concreta (MySQL, MongoDB, H2)
 *  ✓ No conoce el sistema de pagos concreto
 *  ✓ Depende SOLO de interfaces (RepositorioPrestamos, CatalogoBiblioteca, ServicioPagos)
 *  ✓ Es 100% testeable con mocks sin levantar ninguna infraestructura
 *
 * Dependency Rule: las dependencias apuntan hacia adentro.
 * Este use case vive en el círculo interior y no importa nada del exterior.
 *
 * Patrón implícito: REPOSITORY PATTERN
 *  El use case llama a `repositorio.guardar()` sin saber si hay SQL o NoSQL detrás.
 *  Cambiar de MySQL a MongoDB = solo cambiar la implementación de RepositorioPrestamos.
 *  Este use case NO se toca.
 */
public class RegistrarPrestamoUseCase {

    // ── Dependencias inyectadas como INTERFACES (DIP aplicado) ──────────────
    private final RepositorioPrestamos  repositorio;
    private final CatalogoBiblioteca    catalogo;
    private final ServicioPagos         servicioPagos;
    private final AuditoriaLogger       auditoria;

    /**
     * Constructor con inyección de dependencias.
     * En producción Spring/CDI inyecta las implementaciones concretas.
     * En tests se inyectan mocks.
     */
    public RegistrarPrestamoUseCase(RepositorioPrestamos repositorio,
                                    CatalogoBiblioteca catalogo,
                                    ServicioPagos servicioPagos) {
        this.repositorio   = repositorio;
        this.catalogo      = catalogo;
        this.servicioPagos = servicioPagos;
        this.auditoria     = AuditoriaLogger.getInstance();
    }

    /**
     * Ejecuta el caso de uso completo: validar → crear → cobrar → persistir → auditar.
     *
     * @param solicitud Objeto inmutable construido con SolicitudPrestamoBuilder
     * @return          El préstamo creado y persistido
     */
    public Prestamo ejecutar(SolicitudPrestamo solicitud) {

        // 1. Verificar disponibilidad del libro (vía Adapter — sin conocer SOAP)
        var libro = catalogo.buscarLibro(solicitud.getLibro().getIsbn());
        if (!libro.isDisponible()) {
            throw new IllegalStateException("El libro no está disponible: " + libro.getIsbn());
        }

        // 2. Crear entidad de dominio
        Prestamo prestamo = new Prestamo(
                UUID.randomUUID().toString(),
                solicitud.getEstudiante().getId(),
                libro.getIsbn(),
                LocalDate.now(),
                solicitud.getFechaDevolucion()
        );

        // 3. Cobrar fianza al sistema de pagos (vía interfaz — sin conocer REST)
        servicioPagos.cobrarFianza(
                solicitud.getEstudiante().getId(),
                calcularFianza(libro)
        );

        // 4. Persistir en BD (vía interfaz — sin conocer MySQL/MongoDB)
        repositorio.guardar(prestamo);

        // 5. Registrar en auditoría (Singleton)
        auditoria.registrar(
                "PRESTAMO_CREADO isbn=" + libro.getIsbn(),
                solicitud.getEstudiante().getId()
        );

        return prestamo;
    }

    private double calcularFianza(biblioteca.catalogo.Libro libro) {
        return 50.0; // Lógica simplificada; en producción dependería del tipo de libro
    }
}