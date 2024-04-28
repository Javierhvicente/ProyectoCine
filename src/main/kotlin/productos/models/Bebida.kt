package org.example.productos.models

class Bebida(
    val nombre:CategoriaBebida
):Complemento(nombre.toString()) {
    val precio:Double
    init {
        when(nombre){
            CategoriaBebida.REFRESCOS-> precio=3.0
            CategoriaBebida.AGUA->precio=2.0
        }
    }
    override fun toString(): String {
        return "$nombre: $precio €"
    }
}

enum class CategoriaBebida{
    AGUA,
    REFRESCOS
}