package org.example.productos.complementos.mappers

import database.ComplementoEntity
import org.example.productos.complementos.Exceptions.ComplementoException
import org.example.productos.complementos.dto.ComplementoDto
import org.example.productos.models.*
/**
 * El mapper del complemento que almacenamos en la BBDD a un complemento normal
 * @return Comp`lemento
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun ComplementoEntity.toComplemento(): Complemento {
    val _nombre: String = this.nombre
    val _tipo: String = this.tipo
    when (_tipo) {
        "COMIDA" -> {
            when (_nombre) {
                "PALOMITAS" -> return Comida("PALOMITAS",CategoriaComida.PALOMITAS)
                "FRUTOSSECOS" -> return Comida("FRUTOS SECOS",CategoriaComida.FRUTOSSECOS)
                "PATATAS" -> return Comida("PATATAS",CategoriaComida.PATATAS)
            }
        }

        "BEBIDA" -> {
            when (_nombre) {
                "AGUA" -> return Bebida("AGUA",CategoriaBebida.AGUA)
                "REFRESCO" -> return Bebida("REFRESCO",CategoriaBebida.REFRESCOS)
            }
        }

    }
    throw ComplementoException.TipoInvalido("Tipo no 2valido")
}
/**
 * El mapper del complementoDto a uncomplemento normal
 * @return complemento
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun ComplementoDto.toComplemento(): Complemento {
    when(this.nombre){
        "PALOMITAS" -> return Comida("PALOMITAS",CategoriaComida.PALOMITAS)
        "FRUTOS SECOS" -> return Comida("FRUTOS SECOS",CategoriaComida.FRUTOSSECOS)
        "PATATAS" -> return Comida("PATATAS",CategoriaComida.PATATAS)
        "AGUA" -> return Bebida("AGUA",CategoriaBebida.AGUA)
        "REFRESCO" -> return Bebida("REFRESCO",CategoriaBebida.REFRESCOS)
    }
    throw ComplementoException.TipoInvalido("Tipo no valido")
}
