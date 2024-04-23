package org.example.productos.mappers.complemento

import database.ComplemetoEntity
import org.example.productos.exceptions.complemento.ComplemntoException
import org.example.productos.models.*

fun ComplemetoEntity.toComplemento(): Complemento {
    val _nombre: String = this.nombre
    val _tipo: String = this.tipo
    when (_tipo) {
        "COMIDA" -> {
            when (_nombre) {
                "PALOMITAS" -> return Comida(CategoriaComida.PALOMITAS)
                "FRUTOSSECOS" -> return Comida(CategoriaComida.FRUTOSSECOS)
                "PATATAS" -> return Comida(CategoriaComida.PATATAS)
            }
        }

        "BEBIDA" -> {
            when (_nombre) {
                "AGUA" -> return Bebida(CategoriaBebida.AGUA)
                "REFRESCO" -> return Bebida(CategoriaBebida.REFRESCOS)
            }
        }

    }
    throw ComplemntoException.TipoNoValido("Tipo no valido")
}
