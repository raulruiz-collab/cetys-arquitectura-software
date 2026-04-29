Decisión arquitectónica justificada

Los Adaptadores Viven en la Capa de Presentación
La estructura depende de los límites del sistema, mientras que las conexiones siguen reglas creadas en el nivel central. Aunque parezca raro, cada punto de contacto responde a patrones fijos desde dentro.

Catálogo CETYS cambia de SOAP a GraphQL
Cuando el banco actualiza su API, basta con modificar el adaptador vinculado.
La clase `RegistrarPrestamoUseCase` queda intacta. Así la lógica principal sigue segura por diseño, sin alteraciones innecesarias que puedan romper su comportamiento esperado en distintos escenarios del sistema
Por fuera vienen los avances técnicos… ahí justo donde apunta la arquitectura limpia.

Uniendo los cuatro patrones, surge una lógica interna clara. A pesar de sus diferencias, cada pieza encaja sin esfuerzo. Junto con eso, la estructura mantiene sentido por sí misma. Mientras tanto, el todo funciona como unidad estable
- Builder garantiza que nunca llegue una solicitud inválida al use case.
- Factory desacopla la creación de usuarios del código cliente.
- Adapter protege el dominio de los sistemas externos caóticos.
Gracias a Singleton, el registro entero se mantiene unido. Así resulta sencillo seguir cada paso sin perder detalle en el camino.