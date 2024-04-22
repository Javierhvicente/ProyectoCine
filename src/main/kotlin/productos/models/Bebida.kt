package org.example.productos.models

class Bebida(
    val nombre:String,
    val categoria:CategoriaBebida
):Complementos() {
    val precio:Double
    init {
        when(categoria){
            CategoriaBebida.REFRESCOS-> precio=3.0
            CategoriaBebida.AGUA->precio=2.0
        }
    }
}

enum class CategoriaBebida{
    AGUA,
    REFRESCOS
}