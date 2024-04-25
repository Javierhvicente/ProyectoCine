package org.example.cliente.services

import org.example.cliente.Exceptions.ClienteException
import org.example.cliente.cache.CacheCliente
import org.example.cliente.models.Cliente
import org.example.cliente.repositories.ClienteRepository
import org.example.cliente.validators.ClienteValidator
import org.lighthousegames.logging.logging

private val logger = logging()
class ClienteServiceImpl(
    val repo: ClienteRepository,
    val validador: ClienteValidator,
    val cache: CacheCliente<String, Cliente>
): ClienteService {
    override fun getAll(): List<Cliente> {
        logger.debug { "Obteniendo todos los clientes" }
        return repo.findAll()
    }

    override fun getById(id: String): Cliente {
        logger.debug { "Obteniendo cliente con id: $id" }
        return cache.get(id) ?: repo.findById(id)?.also {
            cache.put(id, it)
        }?: throw ClienteException.ClienteNotFoundException("El cliente con id: $id no ha sido encontrado")
    }

    override fun save(cliente: Cliente): Cliente {
        logger.debug { "Guardando cliente $cliente" }
        return validador.validate(cliente).also { repo.save(cliente) }.also { cache.put(cliente.id, cliente) }
    }

    override fun update(id: String, cliente: Cliente): Cliente {
        logger.debug { "Actualizando cliente con id: $id" }
        return repo.update(id, cliente).also { cache.put(id, it!!) }
            ?: throw ClienteException.ClienteNotUpdatedException("No se ha encontrado cliente con id: $id")
    }

    override fun delete(id: String): Cliente {
        logger.debug { "Eliminando cliente con id: $id" }
        return repo.delete(id).also{ cache.remove(id) }
            ?: throw ClienteException.ClienteNotDeletedException("No se ha podido borrar el cliente con id: $id")
    }
}