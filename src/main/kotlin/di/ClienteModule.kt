package org.example.di

import org.example.cliente.cache.CacheCliente
import org.example.cliente.cache.CacheClienteImpl
import org.example.cliente.models.Cliente
import org.example.cliente.repositories.ClienteRepository
import org.example.cliente.repositories.ClienteRepositoryImpl
import org.example.cliente.services.ClienteService
import org.example.cliente.services.ClienteServiceImpl
import org.example.cliente.validators.ClienteValidator
import org.example.config.Config
import org.koin.dsl.module

val clienteModule = module {
    single <ClienteRepository>{ ClienteRepositoryImpl( )  }
    single <ClienteValidator>{ ClienteValidator()  }
    single<CacheCliente<String,Cliente>> { CacheClienteImpl(Config.cacheSize) }

    single<ClienteService> {
        ClienteServiceImpl(
            repo = get(),
            validador = get(),
            cache = get()
        )
    }
}