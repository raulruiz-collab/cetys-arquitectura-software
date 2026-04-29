package biblioteca.domain.ports;

import biblioteca.domain.model.Prestamo;
import java.util.List;
import java.util.Optional;

/**
 * INTERFAZ DEL REPOSITORIO — Capa de Dominio / Use Cases
 *
 * Ubicación arquitectónica: capa de DOMINIO (círculo interno en Clean Architecture).
 *
 * Justificación:
 *  Esta interfaz es definida por la capa de use cases (quien la necesita),
 *  no por la capa de infraestructura (quien la implementa).
 *  Esto cumple la Dependency Rule: las dependencias apuntan hacia adentro.
 *  La BD (MySQL, MongoDB) implementa esta interfaz; nunca al revés.
 *
 * Patrón implícito: REPOSITORY PATTERN
 *  Abstrae el acceso a datos detrás de una interfaz orientada al dominio.
 *  El use case no sabe si hay SQL, NoSQL, archivo o API detrás.
 */
public interface RepositorioPrestamos {

    void guardar(Prestamo prestamo);

    Optional<Prestamo> buscarPorId(String id);

    List<Prestamo> buscarPorEstudiante(String estudianteId);

    List<Prestamo> buscarActivos();

    void actualizar(Prestamo prestamo);

    void eliminar(String id);
}