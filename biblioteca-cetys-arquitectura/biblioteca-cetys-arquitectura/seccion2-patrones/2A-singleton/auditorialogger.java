package biblioteca.auditoria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PATRÓN SINGLETON — AuditoriaLogger
 *
 * Garantiza que exista una única instancia del registro de auditoría
 * en todo el sistema. Usa el enfoque "initialization-on-demand holder"
 * para ser thread-safe sin sincronización costosa.
 *
 * ¿Por qué una sola instancia?
 *  - Si existieran dos instancias simultáneas, los eventos quedarían
 *    fragmentados en dos logs distintos. Una auditoría incompleta
 *    impide detectar accesos no autorizados o errores de integridad.
 *  - Un único objeto centraliza el control de escritura y evita
 *    condiciones de carrera en el registro.
 */
public final class AuditoriaLogger {

    // ── Registro en memoria (en producción sería una BD o archivo) ──────────
    private final List<String> registros = new ArrayList<>();
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ── Constructor PRIVADO ─────────────────────────────────────────────────
    // Al hacerlo privado, ninguna clase externa puede invocar `new AuditoriaLogger()`.
    // Esto hace IMPOSIBLE crear una segunda instancia desde fuera.
    private AuditoriaLogger() {
        System.out.println("[AuditoriaLogger] Instancia única creada.");
    }

    // ── Holder: carga la instancia solo cuando se necesita (lazy + thread-safe) ─
    private static final class Holder {
        private static final AuditoriaLogger INSTANCIA = new AuditoriaLogger();
    }

    /**
     * Punto de acceso global a la única instancia.
     * @return la instancia singleton de AuditoriaLogger
     */
    public static AuditoriaLogger getInstance() {
        return Holder.INSTANCIA;
    }

    // ── Clonar también debe estar bloqueado ─────────────────────────────────
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("No se puede clonar el Singleton AuditoriaLogger.");
    }

    // ── API pública ─────────────────────────────────────────────────────────

    /**
     * Registra un evento de auditoría.
     *
     * @param evento   Descripción de la acción realizada (ej. "PRESTAMO_CREADO")
     * @param usuario  Identificador del usuario que ejecutó la acción
     */
    public synchronized void registrar(String evento, String usuario) {
        String timestamp = LocalDateTime.now().format(FMT);
        String entrada = String.format("[%s] USUARIO: %-20s | EVENTO: %s",
                timestamp, usuario, evento);
        registros.add(entrada);
        System.out.println(entrada);   // En producción: escribir a BD o archivo
    }

    /**
     * Retorna todos los registros de auditoría (inmutable).
     */
    public List<String> obtenerRegistros() {
        return Collections.unmodifiableList(registros);
    }

    // ── Demostración ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        AuditoriaLogger log1 = AuditoriaLogger.getInstance();
        AuditoriaLogger log2 = AuditoriaLogger.getInstance();

        System.out.println("¿Son la misma instancia? " + (log1 == log2)); // true

        log1.registrar("PRESTAMO_CREADO", "estudiante@cetys.mx");
        log2.registrar("MULTA_REGISTRADA", "bibliotecario@cetys.mx");
        log1.registrar("LOGIN_EXITOSO", "admin@cetys.mx");

        System.out.println("\n── Todos los registros ──");
        log1.obtenerRegistros().forEach(System.out::println);
    }
}