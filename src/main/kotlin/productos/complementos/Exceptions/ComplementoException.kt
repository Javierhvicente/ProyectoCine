package org.example.productos.complementos.Exceptions

sealed class ComplementoException(message: String): Exception(message) {
    class TipoInvalido(message: String): ComplementoException(message)
}