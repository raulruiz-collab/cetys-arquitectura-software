package biblioteca.catalogo;

/**
 * INTERFAZ INTERNA que el sistema espera.
 * Ningún componente interno conoce la implementación concreta.
 */
public interface CatalogoBiblioteca {
    /**
     * Busca un libro por su ISBN.
     * @param isbn Código ISBN estándar
     * @return Objeto Libro con los datos del dominio interno
     */
    Libro buscarLibro(String isbn);
}