# Sección 4 — Flujo completo: Solicitud de préstamo con cobro de fianza

## Descripción del flujo
"Un estudiante solicita un préstamo y el sistema cobra la fianza al Sistema de Pagos Bancario."

## Participantes en orden cronológico

| # | Componente/Clase              | Acción                                          | Patrón       | Nivel C4        |
|---|-------------------------------|------------------------------------------------|--------------|-----------------|
| 1 | Estudiante (actor)            | Llena formulario de préstamo en Web App        | —            | Contexto (L1)   |
| 2 | PrestamoController            | Recibe POST /prestamos, parsea el request      | —            | Componente (L3) |
| 3 | SolicitudPrestamoBuilder      | Construye SolicitudPrestamo validando campos   | **Builder**  | Componente (L3) |
| 4 | FabricaDeUsuarios             | Obtiene el objeto Usuario correcto por tipo    | **Factory**  | Componente (L3) |
| 5 | RegistrarPrestamoUseCase      | Orquesta toda la lógica del caso de uso        | —            | Componente (L3) |
| 6 | CatalogoAdapter               | Traduce buscarLibro() → consultarObra() SOAP   | **Adapter**  | Componente (L3) |
| 7 | Catálogo CETYS (externo)      | Responde disponibilidad del libro              | —            | Contexto (L1)   |
| 8 | PagosAdapter                  | Traduce cobrarFianza() → API REST bancaria     | **Adapter**  | Componente (L3) |
| 9 | Sistema de Pagos (externo)    | Procesa el cobro y confirma                    | —            | Contexto (L1)   |
|10 | RepositorioPrestamoJPA        | Persiste el préstamo en PostgreSQL             | Repository   | Componente (L3) |
|11 | AuditoriaLogger.getInstance() | Registra evento en el log único centralizado  | **Singleton**| Componente (L3) |
|12 | Cola de Mensajes (RabbitMQ)   | Publica evento para notificación               | —            | Contenedor (L2) |
|13 | Worker de Notificaciones      | Envía email de confirmación al estudiante      | —            | Contenedor (L2) |

## Diagrama de secuencia simplificado