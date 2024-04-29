package org.example.productos.butaca.repositories

import org.example.database.SqlDelightManager
import org.example.productos.butaca.mappers.toButaca
import org.example.productos.models.Butaca

import org.lighthousegames.logging.logging

private val logger= logging()

/**
 * El repositorio de las butacas
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
class ButacasRepositoryImpl: ButacasRepository {

    private val db  = SqlDelightManager.databaseQueries

    /**
     * devuelve todas las butacas de la base de datos
     * @return List<Butaca>
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun findAll(): List<Butaca> {
        logger.debug { "Obteniendo todas las butacas" }
        return db.getAllButacaEntity().executeAsList().map { it.toButaca() }
    }

    /**
     * devuelve una butaca siguiendo un id
     * @return butaca
     * @param id
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun findById(id: String): Butaca? {
        logger.debug { "Obteniendo butaca por id: $id" }
        return db.getByIdButacaEntity(id)
            .executeAsOneOrNull()
            ?.toButaca()
    }

    /**
     * encontrar una lista de butacas segun el tipo
     * @return lista de butacas
     * @param tipo
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun findByTipo(tipo: String): List<Butaca> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return db.getbutacaByTipo(tipo)
            .executeAsList()
            .map { it.toButaca() }
    }

    /**
     * guradar una butaca
     * @return butaca
     * @param producto
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun save(producto: Butaca): Butaca {
        logger.debug { "Guardando butaca: $producto" }

        db.transaction {
            db.insertarbutaca(
                id = producto.id,
                estado = producto.estado.toString(),
                precio = producto.precio.toLong(),
                tipo = producto.tipo.toString(),
                createAt = producto.create.toString()
            )
        }
        return producto
    }

    /**
     * actualizar una butaca
     * @param id
     * @param butaca
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun update(id: String, butaca: Butaca): Butaca? {
        logger.debug { "Actualizando butaca con id: $id" }
        val result = this.findById(id) ?: return null

        db.updateButacaEntity(
            id = butaca.id,
            estado = butaca.estado.toString(),
            tipo = butaca.tipo.toString(),
            precio = butaca.precio.toLong()
        )
        return result
    }

    /**
     * eliminar una butaca con su id
     * @param id
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun delete(id: String): Butaca? {
        logger.debug { "Borrando butaca con id: $id" }
        val result = this.findById(id) ?: return null
        db.deleteButacaId(id)
        return result
    }

}