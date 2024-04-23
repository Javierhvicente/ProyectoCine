package org.example

import org.example.productos.models.Butaca
import org.example.productos.models.Estado
import org.example.productos.models.Tipo

fun main() {
    val bu=Butaca("a2",Estado.ACTIVA,Tipo.VIP)

    println(bu.estado.toString())
    println(bu.precio)
}