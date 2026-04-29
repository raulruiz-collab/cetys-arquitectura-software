package biblioteca.refactorizacion;

/**
 * INTERFAZ de notificación — permite extensión sin modificación (OCP + DIP).
 */
public interface ServicioNotificacion {
    void enviar(String destinatario, String mensaje);
}