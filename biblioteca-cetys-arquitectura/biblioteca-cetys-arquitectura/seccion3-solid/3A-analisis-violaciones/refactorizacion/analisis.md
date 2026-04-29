# 3A — Análisis de violaciones SOLID en GestorBiblioteca

Principios violados

1. Principio de Responsabilidad Única
Por empezar, la clase maneja siete tareas diferentes
préstamos, devoluciones, multas, email, PDF, autenticación y catálogo CETYS.

Regla: Una clase debe tener una sola razón para cambiar.

Así pasa cuando el encargado del PDF se modifica: igual toca ajustar esa clase.
En esa zona reside el funcionamiento de los créditos. Si hay un error en el correo, todo el sistema de préstamos podría fallar.

2. OCP Principio Abierto Cerrado
Notificación por SMS o WhatsApp necesita cambios si hay violación
`GestorBiblioteca` directamente.

Solución: Extraer `ServicioNotificacion` como interfaz; inyectar implementaciones.

3. DIP Principio de Inversión de Dependencias
Uso directo de clientes SMTP y generadores PDF por la clase
y enlaces LDAP. Lo que importa son los detalles prácticos, nunca las ideas vagas.

La solución implica que cada dependencia externa llegue mediante interfaces
por medio del constructor, usando inyección de dependencias.

4. ISP principio de segregación de interfaces potencial
Podría haber problemas. Supongamos que aparece una clase llamada IGestorBiblioteca, dotada con siete funciones distintas
Quien tan solo precise dinero prestado tendría que aceptar condiciones impuestas por
formas raras para manejar archivos PDF que casi nadie prueba.

Conexión con la regla de dependencia en arquitectura limpia
La refactorización sigue la regla de dependencia
- Los use cases (`GestorPrestamos`) dependen de interfaces (`RepositorioPrestamosInterface`)
En la capa de infraestructura se encuentran herramientas como JPA, SMTP o iText PDF. Aunque parezcan distintas, todas forman parte del mismo nivel técnico. Su función específica depende del contexto donde operen. Mientras una maneja datos, otra envía correos. Cada una resuelve una necesidad particular desde el fondo del sistema.

Desde fuera llegan las dependencias, todas van al centro. Hacia dentro se dirige cada conexión necesaria. Nunca al revés: el núcleo no espera por lo externo. Lo que importa está en medio, lo demás converge ahí. Las flechas del uso nunca salen desde adentro.

Cambia la base de datos o el servicio de correo, y lo único que varía es la parte inferior del sistema.