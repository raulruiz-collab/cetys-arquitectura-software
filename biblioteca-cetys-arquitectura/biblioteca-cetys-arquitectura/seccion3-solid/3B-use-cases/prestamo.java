package biblioteca.domain.model;

import java.time.LocalDate;

/** Entidad de dominio — sin dependencias de framework ni BD. */
public class Prestamo {
    private final String    id;
    private final String    estudianteId;
    private final String    isbn;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaDevolucion;
    private       boolean   activo;

    public Prestamo(String id, String estudianteId, String isbn,
                    LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id              = id;
        this.estudianteId    = estudianteId;
        this.isbn            = isbn;
        this.fechaPrestamo   = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.activo          = true;
    }

    public String    getId()              { return id; }
    public String    getEstudianteId()    { return estudianteId; }
    public String    getIsbn()            { return isbn; }
    public LocalDate getFechaPrestamo()   { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public boolean   isActivo()           { return activo; }
    public void      cerrar()             { this.activo = false; }
}