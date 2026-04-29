package biblioteca.catalogo;

/**
 * PATRÓN ADAPTER — CatalogoAdapter
 *
 * Permite que el sistema interno use `CatalogoBiblioteca.buscarLibro(isbn)`
 * mientras que internamente traduce la llamada a
 * `CatalogoCETYSServicio.consultarObra(codigoCETYS, formato)`.
 *
 * Ninguna de las dos clases originales fue modificada.
 *
 * REFLEXIÓN: Si mañana CETYS cambia de proveedor de catálogo:
 *  → Solo se crea un nuevo adaptador (NuevoCatalogoAdapter).
 *  → El código del sistema interno (RegistrarPrestamoUseCase, etc.) NO cambia,
 *    porque trabaja contra la interfaz CatalogoBiblioteca.
 *  → Solo se reconfigura qué implementación se inyecta (DI / Spring Bean).
 *  → Costo de cambio: 1 clase nueva + 1 línea de configuración.
 */
public class CatalogoAdapter implements CatalogoBiblioteca {

    private final CatalogoCETYSServicio servicioExterno;

    public CatalogoAdapter(CatalogoCETYSServicio servicioExterno) {
        this.servicioExterno = servicioExterno;
    }

    @Override
    public Libro buscarLibro(String isbn) {
        // 1. Traducir ISBN → código CETYS
        String codigoCETYS = servicioExterno.isbnACodigoCETYS(isbn);

        // 2. Llamar al sistema externo con su propia firma
        ResultadoSOAP resultado = servicioExterno.consultarObra(codigoCETYS, "XML");

        // 3. Traducir la respuesta externa al modelo interno
        boolean disponible = "DISPONIBLE".equalsIgnoreCase(resultado.estadoDisponibilidad);

        return new Libro(isbn, resultado.nombreObra, resultado.autorPrincipal, disponible);
    }

    // ── Demostración ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        CatalogoCETYSServicio servicio = new CatalogoCETYSServicio();
        CatalogoBiblioteca catalogo = new CatalogoAdapter(servicio);

        // El código cliente SOLO conoce la interfaz CatalogoBiblioteca
        Libro libro = catalogo.buscarLibro("978-0-07-301775-3");
        System.out.println("Libro encontrado: " + libro);
    }
}