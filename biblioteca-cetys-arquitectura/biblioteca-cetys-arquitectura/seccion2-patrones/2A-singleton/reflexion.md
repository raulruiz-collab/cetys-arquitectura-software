# 2A — Reflexión: Singleton AuditoriaLogger

El constructor terminó apartado, sin más aviso. Sucedió porque las medidas no cuadraban desde un inicio. Nadie revisó los planos a tiempo. Eso derivó en retrasos que nadie pudo detener. Así quedó la situación, sin vuelta atrás.
Imposibilita que cualquier clase acceda al constructor, puesto que este fue marcado como privado
fuera llame a `new AuditoriaLogger()`. Solo es posible conseguirlo mediante.
La instancia se obtiene usando AuditoriaLogger.getInstance().

Por otro lado, quedó inhabilitado el acceso al método `clone()`, con el fin de impedir copias mediante reflexión.

Uno solo evita líos cuando hay más de un lugar haciendo lo mismo al tiempo.
Por exigencia del rector, el registro debe quedar en un solo lugar. Aunque hubiera otro, no serviría
instancias:
Una lista llevaría algunos eventos, mientras que la otra tendría los restantes.
Puede que ninguna tuviera todos los datos registrados.
Parece difícil ordenar paso a paso lo sucedido dentro del sistema.
Sin inspecciones cuidadosas, algo siempre faltaría en los exámenes legales.

¿Qué pasaría con dos instancias simultáneas?
1. Un montón de eventos se perderían en el registro. Solo la mitad llegaría al archivo. El resto quedaría fuera del rastro
La mitad para el resto. Jamás se podrían vincular.
2. En una carrera, pasa cuando dos elementos modifican datos al mismo tiempo sin
Puede que la sincronización cause datos repetidos o dañados.
3. Inconsistencia de datos: Los reportes de auditoría mostrarían información
En parte, esto puede acarrear problemas ante la ley o sanciones internas.