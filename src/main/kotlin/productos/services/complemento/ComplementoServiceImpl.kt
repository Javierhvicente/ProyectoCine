package org.example.productos.services.complemento

import com.github.michaelbull.result.*

import org.example.productos.cache.complemento.ComplementoCache
import org.example.productos.errors.ComplementoError
import org.example.productos.models.Complemento
import org.example.productos.repositories.complemento.ComplementoRepository
import org.example.productos.storage.complemento.ComplementoStorage
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()
class ComplementoServiceImpl(
    private val repository: ComplementoRepository,
    private val cache: ComplementoCache,
    private val storage: ComplementoStorage
): ComplementoService {
    override fun getAll(): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo todos los complementos" }
        return Ok(repository.findAll())
    }

    override fun getByTipo(tipo: String): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complementos por tipo: $tipo" }
        return Ok(repository.findByTipo(tipo))
    }

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
                    ?: Err(ComplementoError.ButacaNoEncontrada("Complemento no encontrado con id: $id"))
            }
        )
    }

    override fun create(complemento: Complemento): Result<Complemento, ComplementoError> {
        logger.debug { "Guardando complemento $complemento" }
        return Ok( repository.save(complemento)).also {
            cache.put(complemento.id,complemento)
        }
    }

    override fun update(id: String, complemento: Complemento): Result<Complemento, ComplementoError> {
        logger.debug { "Actualizando complemento con id: $id" }
        return  repository.update(complemento.id, complemento)
            .also { cache.put(id,complemento) }
                ?.let { Ok(it) }
                ?: Err(ComplementoError.ButacaNoActualizadas("No se ha podido actualizar el complemento: $id"))
    }

    override fun delete(id: String): Result<Complemento, ComplementoError> {
        logger.debug { "Borrando complemento con id $id" }
        return repository.delete(id)
            ?.let {
                cache.remove(id)
                Ok(it)
            }
            ?: Err(ComplementoError.ButacaNoBorradas("El complemento no a sido eliminada $id"))

    }

    override fun import(csvFile: File): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Cargando complemento desde CSV" }
        return storage.load(csvFile).andThen { personajes->
            personajes.forEach{ p->
                repository.save(p)
            }
            Ok(personajes)
        }
    }
}