package org.example.productos.complementos.repositories

import org.example.database.SqlDelightManager
import org.example.productos.complementos.mappers.toComplemento
import org.example.productos.models.Bebida
import org.example.productos.models.Comida
import org.example.productos.models.Complemento


import org.lighthousegames.logging.logging

private val logger= logging()
/**
 * El repositorio de los complementos
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
class ComplementoRepositoryImpl: ComplementoRepository {
    private val db  = SqlDelightManager.databaseQueries

    /**
     * devuelve todas los complementos de la base de datos
     * @return lista de complementos
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun findAll(): List<Complemento> {
        logger.debug { "Obteniendo todas los complementos" }
        return db.getAllComplemetoEntity().executeAsList().map { it.toComplemento() }
    }
    /**
     * devuelve un complemento siguiendo un id
     * @return complemento
     * @param id
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun findById(id: String): Complemento? {
        logger.debug { "Obteniendo complemento por id: $id" }
        return db.getByIdComplemetoEntity(id)
            .executeAsOneOrNull()
            ?.toComplemento()
    }
    /**
     * encontrar una lista de complementos segun el tipo
     * @return lista de complementos
     * @param tipo
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun findByTipo(tipo: String): List<Complemento> {
        logger.debug { "Obteniendo complemento por tipo: $tipo" }
        return db.getComplementoByTipo(tipo).executeAsList().map { it.toComplemento() }
    }
    /**
     * guradar un complemento
     * @return complemento
     * @param producto
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun save(producto: Complemento): Complemento {
        logger.debug { "Guardando complemento: $producto" }

        when(producto){
            is Bebida ->{
                db.transaction {
                    db.insertComplemento(
                        tipo = "BEBIDA",
                        id = producto.id,
                        nombre = producto.nombre.toString(),
                        precio = producto.precio.toLong()
                    )
                    println("llega aqui")
                }
            }
            is Comida ->{
                db.transaction {
                    db.insertComplemento(
                        tipo = "COMIDA",
                        id = producto.id,
                        nombre = producto.nombre.toString(),
                        precio = producto.precio.toLong())
                }
            }
        }
        return producto
    }
    /**
     * actualizar un complemento
     * @param id
     * @param complemento
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun update(id: String, complemento: Complemento): Complemento? {
        logger.debug { "Actualizando complemento con id: $id" }
        val result = this.findById(id) ?: return null

        when(complemento){
            is Bebida ->{
                db.updateComplementoEntity(id,complemento.nombre.toString(),complemento.precio.toLong(),"BEBIDA")
            }
            is Comida ->{
                db.updateComplementoEntity(id,complemento.nombre.toString(),complemento.precio.toLong(),"COMIDA")
            }
        }

        return result
    }

    /**
     * eliminar un complemento con su id
     * @param id
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun delete(id: String): Complemento? {
        logger.debug { "Borrando complemento con id: $id" }
        val result = this.findById(id) ?: return null
        db.deleteComplementoByID(id)
        return result
    }
}