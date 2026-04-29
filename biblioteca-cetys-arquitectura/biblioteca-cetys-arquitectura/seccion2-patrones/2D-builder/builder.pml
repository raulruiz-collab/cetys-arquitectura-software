@startuml
class SolicitudPrestamo {
    - SolicitudPrestamo()
}

class Builder {
    + estudiante(e: String): Builder
    + libro(l: String): Builder
    + fecha(f: String): Builder
    + notas(n: String): Builder
    + renovacion(r: boolean): Builder
    + construir(): SolicitudPrestamo
}

Builder --> SolicitudPrestamo
@enduml