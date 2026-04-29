package biblioteca.refactorizacion;

import biblioteca.auditoria.AuditoriaLogger;

/**
 * Responsabilidad única: calcular y registrar multas.
 */
public class GestorMultas {

    private final AuditoriaLogger auditoria = AuditoriaLogger.getInstance();

    public void registrarMulta(String usuarioId, double monto) {
        // lógica de multa
        auditoria.registrar("MULTA_REGISTRADA monto=" + monto, usuarioId);
    }
}