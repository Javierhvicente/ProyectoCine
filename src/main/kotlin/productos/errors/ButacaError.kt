<<<<<<< Updated upstream
package org.example.productos.errors.butaca
=======
package org.example.productos.errors
>>>>>>> Stashed changes

sealed class ButacaError(val mensage:String) {
    class IdNoValido(mensage: String):ButacaError(mensage)
}