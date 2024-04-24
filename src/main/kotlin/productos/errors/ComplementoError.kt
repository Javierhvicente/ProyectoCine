package org.example.productos.errors

import org.example.productos.errors.butaca.ButacaError

sealed class ComplementoError(val mensaje: String) {
    class IdNoValido(mensage: String): ComplementoError(mensage)
    class TipoInvalido(mensage: String): ComplementoError(mensage)
    class FechaInvalido(mensage: String): ComplementoError(mensage)
    class FicheroNoValido(mensage: String): ComplementoError(mensage)
    class ButacaNoEncontrada(mensage: String): ComplementoError(mensage)
    class ButacaNoBorradas(mensage: String): ComplementoError(mensage)
    class ButacaNoActualizadas(mensage: String): ComplementoError(mensage)
}