package org.example.productos.butaca.repositories

import org.example.database.SqlDelightManager
import org.example.productos.butaca.mappers.toButaca
import org.example.productos.models.Butaca

import org.lighthousegames.logging.logging

private val logger= logging()

/**
 * El repositorio de las butacas
 */
class ButacasRepositoryImpl: ButacasRepository {

    private val db  = SqlDelightManager.databaseQueries

    override fun findAll(): List<Butaca> {
        logger.debug { "Obteniendo todas las butacas" }
        return db.getAllButacaEntity().executeAsList().map { it.toButaca() }
    }

    override fun findById(id: String): Butaca? {
        logger.debug { "Obteniendo butaca por id: $id" }
        return db.getByIdButacaEntity(id)
            .executeAsOneOrNull()
            ?.toButaca()
    }

    override fun findByTipo(tipo: String): List<Butaca> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return db.getbutacaByTipo(tipo)
            .executeAsList()
            .map { it.toButaca() }
    }

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

    override fun delete(id: String): Butaca? {
        logger.debug { "Borrando butaca con id: $id" }
        val result = this.findById(id) ?: return null
        db.deleteButacaId(id)
        return result
    }

}