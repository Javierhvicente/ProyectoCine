package org.example.cliente.cache

interface CacheCliente<String, Cliente> {
    fun put(key: kotlin.String, cliente: Cliente)
    fun get(key: kotlin.String): Cliente?
    fun remove(key: kotlin.String ): Cliente?
    fun clear()
}