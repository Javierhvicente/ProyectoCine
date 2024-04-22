package org.example.productos.models

class Comida(
    val nombre:String,
    val categoria:CategoriaComida
):Complementos() {
    val precio:Double
    init {
        when(categoria){
            CategoriaComida.PALOMITAS-> precio=3.0
            CategoriaComida.FRUTOSSECOS->precio=2.0
            CategoriaComida.PATATAS-> precio=2.5
        }
    }
}
enum class CategoriaComida{
    PALOMITAS,
    FRUTOSSECOS,
    PATATAS
}