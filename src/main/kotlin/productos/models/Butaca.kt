package org.example.productos.models

class Butaca(
    val id:String,
    val estado: Estado,
    val tipo:Tipo
):Producto() {
    val precio:Double
    var ocupacion: Ocupacion
    init {
        when(this.tipo){
            Tipo.VIP-> precio=8.0
            Tipo.NORMAL->precio=5.0
        }
        if (estado == Estado.MANTENIMIENTO || estado== Estado.OUTSERVICE){
            ocupacion = Ocupacion.INACTIVA
        }else{
            ocupacion=Ocupacion.LIBRE
        }
    }
}
enum class Estado{
    ACTIVA,
    MANTENIMIENTO,
    OUTSERVICE
}

enum class Ocupacion{
    LIBRE,
    RESERVA,
    OCUPADA,
    INACTIVA
}
enum class Tipo{
    NORMAL,
    VIP
}
