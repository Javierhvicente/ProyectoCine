package org.example.productos.repositories.complemento

import org.example.database.SqlDelightManager
import org.example.productos.mappers.complemento.toComplemento
import org.example.productos.models.Bebida
import org.example.productos.models.Comida
import org.example.productos.models.Complemento
import org.lighthousegames.logging.logging

private val logger= logging()

class ComplementoRepositoryImpl: ComplementoRepository {
    private val db  = SqlDelightManager().databaseQueries


    override fun findAll(): List<Complemento> {
        logger.debug { "Obteniendo todas los complementos" }
        return db.getAllComplemetoEntity().executeAsList().map { it.toComplemento() }
    }

    override fun findById(id: String): Complemento? {
        logger.debug { "Obteniendo complemento por id: $id" }
        return db.getByIdComplemetoEntity(id.toLong())
            .executeAsOneOrNull()
            ?.toComplemento()
    }

    override fun findByTipo(tipo: String): List<Complemento> {
        logger.debug { "Obteniendo complemento por tipo: $tipo" }
        return db.getComplementoByTipo(tipo).executeAsList().map { it.toComplemento() }
    }

    override fun save(producto: Complemento): Complemento {
        logger.debug { "Guardando complemento: $producto" }

        when(producto){
            is Bebida->{
                db.transaction {
                    db.insertComplemento("BEBIDA",producto.nombre.toString(),producto.precio.toLong())
                }
            }
            is Comida->{
                db.transaction {
                    db.insertComplemento("COMIDA",producto.nombre.toString(),producto.precio.toLong())
                }
            }
        }
        return producto
    }

    override fun update(id: String, complemento: Complemento): Complemento? {
        logger.debug { "Actualizando complemento con id: $id" }
        val result = this.findById(id) ?: return null

        when(complemento){
            is Bebida->{
                db.updateComplementoEntity(id.toLong(),complemento.nombre.toString(),complemento.precio.toLong(),"BEBIDA")
            }
            is Comida->{
                db.updateComplementoEntity(id.toLong(),complemento.nombre.toString(),complemento.precio.toLong(),"COMIDA")
            }
        }

        return result
    }

    override fun delete(id: String): Complemento? {
        logger.debug { "Borrando complemento con id: $id" }
        val result = this.findById(id) ?: return null
        db.deleteComplementoByID(id.toLong())
        return result
    }
}