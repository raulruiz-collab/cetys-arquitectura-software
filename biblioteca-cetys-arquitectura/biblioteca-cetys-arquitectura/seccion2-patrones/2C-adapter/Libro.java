package biblioteca.catalogo;

/** Modelo interno del dominio. */
public class Libro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final boolean disponible;

    public Libro(String isbn, String titulo, String autor, boolean disponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    public String getIsbn()       { return isbn; }
    public String getTitulo()     { return titulo; }
    public String getAutor()      { return autor; }
    public boolean isDisponible() { return disponible; }

    @Override
    public String toString() {
        return String.format("Libro[isbn=%s, titulo='%s', autor='%s', disponible=%b]",
                isbn, titulo, autor, disponible);
    }
}