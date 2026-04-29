package biblioteca.refactorizacion;

/** Abstracción del repositorio — DIP: las capas altas dependen de esto, no de JPA/MySQL. */
public interface RepositorioPrestamosInterface {
    void guardar(Object prestamo);
    Object buscarPorId(String id);
}