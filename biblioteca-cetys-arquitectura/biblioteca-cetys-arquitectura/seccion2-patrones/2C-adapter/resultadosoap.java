package biblioteca.catalogo;

/**
 * Clase que simula la respuesta del servicio SOAP del Catálogo CETYS.
 * Representa la estructura externa que NO podemos modificar.
 */
public class ResultadoSOAP {
    public String codigoCETYS;
    public String nombreObra;
    public String autorPrincipal;
    public String estadoDisponibilidad; // "DISPONIBLE" | "PRESTADO" | "RESERVADO"
    public String formato;

    public ResultadoSOAP(String codigoCETYS, String nombreObra,
                         String autorPrincipal, String estadoDisponibilidad, String formato) {
        this.codigoCETYS = codigoCETYS;
        this.nombreObra = nombreObra;
        this.autorPrincipal = autorPrincipal;
        this.estadoDisponibilidad = estadoDisponibilidad;
        this.formato = formato;
    }
}