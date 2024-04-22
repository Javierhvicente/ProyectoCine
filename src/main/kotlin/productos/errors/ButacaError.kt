package org.example.productos.errors.butaca

sealed class ButacaError(val mensage:String) {
    class IdNoValido(mensage: String):ButacaError(mensage)
}