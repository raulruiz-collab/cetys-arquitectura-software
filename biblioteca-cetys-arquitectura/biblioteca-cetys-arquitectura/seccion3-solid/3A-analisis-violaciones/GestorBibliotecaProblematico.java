package biblioteca.violaciones;

/**
 * CLASE PROBLEMÁTICA — Solo para análisis de violaciones SOLID.
 * Esta clase NO debe usarse en producción.
 *
 * VIOLACIONES DETECTADAS:
 *
 * 1. SRP (Single Responsibility Principle) — VIOLADO
 *    GestorBiblioteca tiene 7 responsabilidades distintas:
 *    préstamos, devoluciones, multas, email, PDF, autenticación y catálogo.
 *    Cada una es una razón distinta para modificar la clase.
 *
 * 2. OCP (Open/Closed Principle) — VIOLADO
 *    Agregar un nuevo tipo de notificación (SMS, WhatsApp) requiere
 *    modificar directamente esta clase.
 *
 * 3. DIP (Dependency Inversion Principle) — VIOLADO
 *    La clase depende directamente de implementaciones concretas
 *    (SMTP, generador PDF, LDAP) en lugar de abstracciones (interfaces).
 *
 * 4. ISP (Interface Segregation Principle) — POTENCIALMENTE VIOLADO
 *    Si esta clase implementara una interfaz única con todos estos métodos,
 *    los clientes que solo necesitan préstamos se verían forzados a depender
 *    de métodos de email o PDF que no usan.
 */
public class GestorBibliotecaProblematico {

    // Mezcla de responsabilidades en una sola clase ← VIOLACIÓN SRP
    public void prestarLibro()              { /* lógica de préstamo */ }
    public void devolverLibro()             { /* lógica de devolución */ }
    public void registrarMulta()            { /* lógica de multas */ }
    public void enviarEmailNotificacion()   { /* SMTP directo ← VIOLACIÓN DIP */ }
    public void generarReportePDF()         { /* generador PDF concreto ← VIOLACIÓN DIP */ }
    public void autenticarUsuario()         { /* LDAP directo ← VIOLACIÓN DIP */ }
    public void consultarCatalogoCETYS()    { /* SOAP directo ← VIOLACIÓN DIP */ }
}