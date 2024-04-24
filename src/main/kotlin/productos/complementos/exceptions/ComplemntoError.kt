package org.example.productos.complementos.exceptions

sealed class ComplemntoException(val mensage:String):Exception(mensage) {
    class TipoNoValido(mensage: String): ComplemntoException(mensage)
}