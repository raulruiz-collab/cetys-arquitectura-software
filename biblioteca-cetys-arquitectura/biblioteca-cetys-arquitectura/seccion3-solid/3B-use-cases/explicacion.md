# 3B — Clean Architecture: RegistrarPrestamoUseCase

¿Por qué la interfaz RepositorioPrestamos está en la capa de dominio?
El caso de uso - esa capa interior - requiere el acceso al repositorio, nunca la otra parte
se pone en marcha desde la infraestructura. Quien usa marca cómo debe ser la interfaz,
porque no viene del proveedor. Así funciona invertir las dependencias en este caso
En arquitectura, el dominio marca lo que hace la infraestructura; jamás sucede a la inversa. Por eso, esta última solo responde a aquél.

Pensando en cómo funciona la regla de dependencia, esta evita que alterar MySQL por MongoDB afecte al caso de uso. Al invertir las capas, lo importante queda protegido. La lógica central ignora si se usa un gestor u otro. Gracias a eso, intercambiar bases pasa sin dejar rastro. El núcleo nunca pregunta qué base hay debajo. Por esto mismo, una modificación así ni siquiera llega a molestar.
Donde mira el código depende de la regla mencionada. Esa norma define su dirección permitida
dentro, apuntando a decisiones más altas. Quedaría así el esquema:

[MongoDB Impl] → [RepositorioPrestamos interface] ← [RegistrarPrestamoUseCase]

El caso de uso para registrar un préstamo trabaja junto al repositorio de préstamos mediante una interfaz.
Puede que `MongoDBRepositoryImpl` acabe adoptando esa interfaz, aunque también podría adaptarla sin seguirla del todo.
Al reemplazar MySQL con MongoDB, basta crear una versión distinta.
- `RegistrarPrestamoUseCase` no importa, no referencia y no sabe de MongoDB.
Una clase nueva aparece al cambiar la base de datos. Cero ajustes en las clases del dominio ocurren durante el proceso. Lo nuevo entra sin alterar lo existente allí dentro.

Surge un diseño sin decirlo.
REPOSITORY PATTERN: abstrae el almacenamiento detrás de una interfaz
dirigida por el área específica. La situación se explica usando palabras del entorno comercial
No se trata de tablas ni colecciones, más bien guarda el préstamo mientras busca los activos.