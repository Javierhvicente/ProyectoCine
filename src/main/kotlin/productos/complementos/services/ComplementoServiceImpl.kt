package org.example.productos.complementos.services

import com.github.michaelbull.result.*
import org.example.productos.complementos.cache.ComplementoCacheImpl
import org.example.productos.complementos.errors.ComplementoError
import org.example.productos.complementos.repositories.ComplementoRepository
import org.example.productos.models.Complemento
import org.example.productos.complementos.storage.ComplementoStorage
import java.io.File
import org.lighthousegames.logging.logging

private val logger = logging()
/**
 * servicio de complementos
 * @param repository
 * @param cache
 * @param storage
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
class ComplementoServiceImpl(
    private val repository: ComplementoRepository,
    private val cache: ComplementoCacheImpl,
    private val storage: ComplementoStorage
): ComplementoService {
    /**
     * nos devuelve una lista de complementos
     * @see repository
     * @return lista de complementos
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getAll(): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo todos los complementos" }
        return Ok(repository.findAll())
    }
    /**
     * nos devuelve una lista de complementos segun su tipo
     * @see repository
     * @param tipo
     * @return lista de complementos
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getByTipo(tipo: String): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complementos por tipo: $tipo" }
        return Ok(repository.findByTipo(tipo))
    }
    /**
     * nos devuelve un complemento segun su id
     * @see cache
     * @see repository
     * @param id
     * @return complemento
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getById(id: String): Result<Complemento, ComplementoError> {
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Complemento encontrado en cache" }
                Ok(it)
            },
            failure = {
                logger.debug { "Complemento no encontrado en cache" }
                repository.findById(id)
                    ?.let { Ok(it) }
                    ?: Err(ComplementoError.ComplementoNoEncontrado("Complemento no encontrado con id: $id"))
            }
        )
    }
    /**
     * nos crea un complemento
     * @param complemento
     * @see validador
     * @see cache
     * @see repository
     * @return complemento
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun create(complemento: Complemento): Result<Complemento, ComplementoError> {
        logger.debug { "Guardando complemento $complemento" }
        return Ok(repository.save(complemento)).also {
            cache.put(complemento.id,complemento)
        }
    }
    /**
     * actualizamos un complemento
     * @param id
     * @param complemento
     * @return complemento
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun update(id: String, complemento: Complemento): Result<Complemento, ComplementoError> {
        logger.debug { "Actualizando complemento con id: $id" }
        return  repository.update(complemento.id, complemento)
            .also { cache.put(id,complemento) }
                ?.let { Ok(it) }
                ?: Err(ComplementoError.ComplementoNoActualizado("No se ha podido actualizar el complemento: $id"))
    }
    /**
     * eliminamos un complemento
     * @param id
     * @return complemento
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun delete(id: String): Result<Complemento, ComplementoError> {
        logger.debug { "Borrando complemento con id $id" }
        return repository.delete(id)
            ?.let {
                cache.remove(id)
                Ok(it)
            }
            ?: Err(ComplementoError.ComplementoNoEncontrado("El complemento no a sido eliminada $id"))

    }
    /**
     * leemos un fichero que contiene complementos
     * @param csvFile
     * @return lista de complementos
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun import(csvFile: File): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Cargando complemento desde CSV" }
        return storage.load(csvFile).andThen { personajes->
            personajes.forEach{ p->
                repository.save(p)
                println("guardado $p")
            }
            Ok(personajes)
        }
    }
}