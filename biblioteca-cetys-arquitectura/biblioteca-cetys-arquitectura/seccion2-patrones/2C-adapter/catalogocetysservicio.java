package biblioteca.catalogo;

/**
 * SISTEMA EXTERNO — Catálogo CETYS.
 * Clase que NO podemos modificar. Expone su propia API SOAP.
 */
public class CatalogoCETYSServicio {

    /**
     * Método del servicio SOAP externo.
     * @param codigoCETYS  Código interno del catálogo CETYS (diferente al ISBN)
     * @param formato      Formato de respuesta deseado ("XML", "JSON_LEGACY")
     * @return             ResultadoSOAP con la estructura propia del sistema externo
     */
    public ResultadoSOAP consultarObra(String codigoCETYS, String formato) {
        // Simulación de llamada SOAP al sistema externo
        System.out.println("[CatalogoCETYS SOAP] Consultando obra: " + codigoCETYS);
        return new ResultadoSOAP(
                codigoCETYS,
                "Ingeniería de Software",
                "Pressman, Roger",
                "DISPONIBLE",
                formato
        );
    }

    /**
     * Convierte un ISBN estándar al código interno CETYS.
     * (Lógica propia del sistema externo)
     */
    public String isbnACodigoCETYS(String isbn) {
        // En producción consultaría una tabla de equivalencias interna
        return "CETYS-" + isbn.replace("-", "");
    }
}