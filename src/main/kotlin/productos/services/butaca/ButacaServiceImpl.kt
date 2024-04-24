package org.example.productos.services.butaca

import com.github.michaelbull.result.*
import org.example.productos.cache.butaca.ButacasCache
import org.example.productos.errors.butaca.ButacaError
import org.example.productos.errors.butaca.ButacaError.*
import org.example.productos.models.Butaca
import org.example.productos.repositories.butaca.ButacasRepository
import org.example.productos.storage.butaca.ButacaStorage
import org.example.productos.validator.ButacaValidator
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()
class ButacaServiceImpl(
    private val repository:ButacasRepository,
    private val validador:ButacaValidator,
    private val cache:ButacasCache,
    private val storage:ButacaStorage
):ButacaService {
    override fun getAll(): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo todas las butacas" }
        return Ok(repository.findAll())
    }

    override fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return Ok(repository.findByTipo(tipo))
    }

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
                    ?: Err(ButacaNoEncontrada("Butaca no encontrada con id: $id"))
            }
        )
    }

    override fun create(butaca: Butaca): Result<Butaca, ButacaError> {
        logger.debug { "Guardando butaca $butaca" }
        return validador.validarButaca(butaca).andThen {
            Ok(repository.save(it))
        }.andThen { p ->
            cache.put(p.id, p )
        }
    }

    override fun update(id: String, butaca: Butaca): Result<Butaca, ButacaError> {
        logger.debug { "Actualizando butaca con id: $id" }
        return validador.validarButaca(butaca).andThen {  b ->
            repository.update(b.id, b)
                ?.let { Ok(it) }
                ?: Err(ButacaNoActualizadas("No se ha podido actualizar la butaca: $id"))
        }.andThen {
            cache.put(id, butaca)
        }
    }

    override fun delete(id: String): Result<Butaca, ButacaError> {
        logger.debug { "Borrando butaca con id $id" }
        return repository.delete(id)
            ?.let {
                cache.remove(id)
                Ok(it)
            }
            ?: Err(ButacaNoBorradas("La butaca no a sido eliminada $id"))
    }

    override fun import(csvFile: File): Result<List<Butaca>, ButacaError> {
        logger.debug { "Cargando butacas desde CSV" }
        return storage.load(csvFile).andThen { personajes->
            personajes.forEach{ p->
                repository.save(p)
            }
            Ok(personajes)
        }
    }

    override fun export(fecha: String, list: List<Butaca>): Result<Unit, ButacaError> {
        logger.debug { "Guardando personajes en JSON" }
        return storage.save(fecha,list)
    }
}