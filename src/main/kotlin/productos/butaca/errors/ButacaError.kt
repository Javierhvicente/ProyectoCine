
package org.example.productos.errors.butaca

/**
 * la errores de butacas
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
sealed class ButacaError(val mensage:String) {
    class IdNoValido(mensage: String):ButacaError(mensage)
    class TipoInvalido(mensage: String):ButacaError(mensage)
    class FechaInvalido(mensage: String):ButacaError(mensage)
    class FicheroNoValido(mensage: String):ButacaError(mensage)
    class ButacaNoEncontrada(mensage: String):ButacaError(mensage)
    class ButacaNoBorradas(mensage: String):ButacaError(mensage)
    class ButacaNoActualizadas(mensage: String):ButacaError(mensage)
}