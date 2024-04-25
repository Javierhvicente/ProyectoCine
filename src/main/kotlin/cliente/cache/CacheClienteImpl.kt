package org.example.cliente.cache

import org.example.cliente.models.Cliente
import org.example.config.Config
import org.lighthousegames.logging.logging

private val logger = logging()
class CacheClienteImpl(
    private val size: Int
): CacheCliente<String, Cliente> {
    private val cache: MutableMap<String, Cliente> = mutableMapOf()
    override fun put(key: String, cliente: Cliente) {
        logger.debug { "Guardando cliente en cache con id $key" }
        if (cache.size >= Config.cacheSize && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            logger.debug { "Eliminando persona en cache con id $firstKey porque está llena" }
            cache.remove(firstKey)
        }
        cache[key] = cliente
    }

    override fun get(key: String): Cliente? {
        logger.debug { "Obteniendo cliente en cache con id $key" }
        return cache[key]
    }

    override fun remove(key: String): Cliente? {
        logger.debug { "Eliminando cliente con id: $key" }
        return cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando cache de Clientes" }
        return cache.clear()
    }

}