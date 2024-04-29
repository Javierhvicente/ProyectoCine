package org.example.cliente.services

import org.example.cliente.Exceptions.ClienteException
import org.example.cliente.cache.CacheCliente
import org.example.cliente.models.Cliente
import org.example.cliente.repositories.ClienteRepository
import org.example.cliente.validators.ClienteValidator
import org.lighthousegames.logging.logging

private val logger = logging()
/**
 * servicio de cliente
 * @param  repo
 * @param  validador
 * @param  cache
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
class ClienteServiceImpl(
    private val repo: ClienteRepository,
    private val validador: ClienteValidator,
    private val cache: CacheCliente<String, Cliente>
): ClienteService {
    /**
     * nos devuelve una lista de cliente
     * @see repo
     * @return lista de cliente
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getAll(): List<Cliente> {
        logger.debug { "Obteniendo todos los clientes" }
        return repo.findAll()
    }
    /**
     * nos devuelve un cliente segun su id
     * @see cache
     * @see repo
     * @param id
     * @return cliente
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun getById(id: String): Cliente {
        logger.debug { "Obteniendo cliente con id: $id" }
        return cache.get(id) ?: repo.findById(id)?.also {
            cache.put(id, it)
        }?: throw ClienteException.ClienteNotFoundException("El cliente con id: $id no ha sido encontrado")
    }
    /**
     * nos guarda un cliente
     * @param cliente
     * @see validador
     * @see cache
     * @see repo
     * @return cliente
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun save(cliente: Cliente): Cliente {
        logger.debug { "Guardando cliente $cliente" }
        return validador.validate(cliente).also { repo.save(cliente) }.also { cache.put(cliente.id, cliente) }
    }
    /**
     * actualizamos un cliente
     * @param id
     * @param cliente
     * @return cliente
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun update(id: String, cliente: Cliente): Cliente {
        logger.debug { "Actualizando cliente con id: $id" }
        return repo.update(id, cliente).also { cache.put(id, it!!) }
            ?: throw ClienteException.ClienteNotUpdatedException("No se ha encontrado cliente con id: $id")
    }
    /**
     * eliminamos un cliente
     * @param id
     * @return cliente
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun delete(id: String): Cliente {
        logger.debug { "Eliminando cliente con id: $id" }
        return repo.delete(id).also{ cache.remove(id) }
            ?: throw ClienteException.ClienteNotDeletedException("No se ha podido borrar el cliente con id: $id")
    }
}