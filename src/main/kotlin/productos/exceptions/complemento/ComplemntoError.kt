package org.example.productos.exceptions.complemento

sealed class ComplemntoException(val mensage:String):Exception(mensage) {
    class TipoNoValido(mensage: String): ComplemntoException(mensage)
}