package org.example.productos.butaca.services

import com.github.michaelbull.result.*
import org.example.productos.butaca.cache.ButacasCacheImpl
import org.example.productos.butaca.repositories.ButacasRepository
import org.example.productos.errors.butaca.ButacaError
import org.example.productos.models.Butaca
import org.example.productos.butaca.storage.ButacaStorage
import org.example.productos.butaca.validator.ButacaValidator
import java.io.File
import org.lighthousegames.logging.logging

private val logger = logging()

/**
 * servicio de butacas
 * @param  repository
 * @param  validador
 * @param  cache
 * @param storage
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
class ButacaServiceImpl(
    private val repository: ButacasRepository,
    private val validador: ButacaValidator,
    private val cache: ButacasCacheImpl,
    private val storage: ButacaStorage
): ButacaService {
    /**
     * nos devuelve una lista de butacas
     * @see repository
     * @return lista de butacas
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getAll(): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo todas las butacas" }
        return Ok(repository.findAll())
    }

    /**
     * nos devuelve una lista de butacas segun su tipo
     * @see repository
     * @param tipo
     * @return lista de butacas
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return Ok(repository.findByTipo(tipo))
    }

    /**
     * nos devuelve una butaca segun su id
     * @see cache
     * @see repository
     * @param id
     * @return butaca
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getById(id: String): Result<Butaca, ButacaError> {
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Butaca encontrada en cache" }
                Ok(it)
            },
            failure = {
                logger.debug { "Butaca no encontrada en cache" }
                repository.findById(id)
                    ?.let { Ok(it) }
                    ?: Err(ButacaError.ButacaNoEncontrada("Butaca no encontrada con id: $id"))
            }
        )
    }

    /**
     * nos crea una butaca
     * @param butaca
     * @see validador
     * @see cache
     * @see repository
     * @return butaca
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun create(butaca: Butaca): Result<Butaca, ButacaError> {
        logger.debug { "Guardando butaca $butaca" }
        return validador.validarButaca(butaca).andThen {
            Ok(repository.save(it))
        }.andThen { p ->
            cache.put(p.id, p )
        }
    }

    /**
     * actualizamos una butaca
     * @param id
     * @param butaca
     * @return butaca
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun update(id: String, butaca: Butaca): Result<Butaca, ButacaError> {
        logger.debug { "Actualizando butaca con id: $id" }
        return validador.validarButaca(butaca).andThen {  b ->
            repository.update(b.id, b)
                ?.let { Ok(it) }
                ?: Err(ButacaError.ButacaNoActualizadas("No se ha podido actualizar la butaca: $id"))
        }.andThen {
            cache.put(id, butaca)
        }
    }

    /**
     * eliminamos una butaca
     * @param id
     * @return butaca
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun delete(id: String): Result<Butaca, ButacaError> {
        logger.debug { "Borrando butaca con id $id" }
        return repository.delete(id)
            ?.let {
                cache.remove(id)
                Ok(it)
            }
            ?: Err(ButacaError.ButacaNoBorradas("La butaca no a sido eliminada $id"))
    }

    /**
     * leemos un fichero que contiene butacas
     * @param csvFile
     * @return lista de butacas
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun import(csvFile: File): Result<List<Butaca>, ButacaError> {
        logger.debug { "Cargando butacas desde CSV" }
        return storage.load(csvFile).andThen { butacas->
            butacas.forEach{ p->
                repository.save(p)
            }
            Ok(butacas)
        }
    }

    /**
     * exportamos una lista de butacas a un json
     * @param fecha
     * @param list
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun export(fecha: String, list: List<Butaca>): Result<Unit, ButacaError> {
        logger.debug { "Guardando personajes en JSON" }
        return storage.save(fecha,list)
    }
}