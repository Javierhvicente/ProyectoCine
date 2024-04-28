package org.example

import org.example.cliente.cache.CacheClienteImpl
import org.example.cliente.repositories.ClienteRepositoryImpl
import org.example.cliente.services.ClienteServiceImpl
import org.example.cliente.validators.ClienteValidator
import org.example.config.Config

fun main() {
    val clienteService = ClienteServiceImpl(
        ClienteRepositoryImpl(),
        cache =  CacheClienteImpl(Config.cacheSize),
        validador =  ClienteValidator()
    )
    clienteService.getAll()

}