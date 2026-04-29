# 2B — Reflexión: Factory y Principio OCP

## ¿Qué principio SOLID garantiza la extensibilidad?
**OCP — Open/Closed Principle**: Las entidades de software deben estar abiertas
para extensión pero cerradas para modificación.

## ¿Cómo se agrega el tipo Posgrado sin tocar código existente?
1. Se crea `Posgrado.java` implementando `Usuario` — clase nueva, nada modificado.
2. Se agrega `case "POSGRADO"` en `FabricaDeUsuarios` — un solo switch, sin tocar
   las clases Estudiante, Bibliotecario ni Admin.
3. El código cliente (`RegistrarPrestamoUseCase`, etc.) no cambia en absoluto
   porque trabaja contra la interfaz `Usuario`, no contra clases concretas.