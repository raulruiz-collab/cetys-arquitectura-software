package biblioteca.usecases;

/**
 * INTERFAZ del servicio de pagos.
 * Vive en la capa de dominio/use cases.
 * La implementación concreta (REST al banco) vive en infraestructura.
 */
public interface ServicioPagos {
    void cobrarFianza(String estudianteId, double monto);
    void cobrarMulta(String estudianteId, double monto);
}