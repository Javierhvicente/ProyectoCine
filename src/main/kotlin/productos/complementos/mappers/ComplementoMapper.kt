package org.example.productos.complementos.mappers

import database.ComplementoEntity
import org.example.productos.complementos.Exceptions.ComplementoException
import org.example.productos.complementos.dto.ComplementoDto
import org.example.productos.models.*

fun ComplementoEntity.toComplemento(): Complemento {
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
    throw ComplementoException.TipoInvalido("Tipo no valido")
}

fun ComplementoDto.toComplemento(): Complemento {
    when(this.nombre){
        "PALOMITAS" -> return Comida(CategoriaComida.PALOMITAS)
        "FRUTOS SECOS" -> return Comida(CategoriaComida.FRUTOSSECOS)
        "PATATAS" -> return Comida(CategoriaComida.PATATAS)
        "AGUA" -> return Bebida(CategoriaBebida.AGUA)
        "REFRESCO" -> return Bebida(CategoriaBebida.REFRESCOS)
    }
    throw ComplementoException.TipoInvalido("Tipo no valido")
}

fun Complemento.toDto(): ComplementoDto {
    when(this){
        is Bebida->{
            when(this.nombre){
                CategoriaBebida.AGUA -> return ComplementoDto("BEBIDA","AGUA",this.precio.toString())
                CategoriaBebida.REFRESCOS-> return ComplementoDto("BEBIDA","REFRESCO",this.precio.toString())
            }
        }
        is Comida->{
            when(this.nombre){
                CategoriaComida.PALOMITAS-> return ComplementoDto("COMIDA","PALOMITAS",this.precio.toString())
                CategoriaComida.PATATAS-> return ComplementoDto("COMIDA","PATATAS",this.precio.toString())
                CategoriaComida.FRUTOSSECOS-> return ComplementoDto("COMIDA","FRUTOS SECOS",this.precio.toString())
            }
        }
        else -> throw ComplementoException.TipoInvalido("Tipo no valido")
    }
}
