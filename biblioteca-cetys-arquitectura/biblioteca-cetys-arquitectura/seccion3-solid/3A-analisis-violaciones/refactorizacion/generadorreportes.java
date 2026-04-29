package biblioteca.refactorizacion;

/** Responsabilidad única: generación de reportes. Separada de la lógica de negocio. */
public class GeneradorReportes {
    public void generarReportePDF(String contenido) {
        System.out.println("[PDF] Generando reporte: " + contenido);
    }
}