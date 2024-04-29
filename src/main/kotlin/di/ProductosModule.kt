package org.example.di

import org.example.config.Config
import org.example.productos.butaca.cache.ButacasCacheImpl
import org.example.productos.butaca.repositories.ButacasRepository
import org.example.productos.butaca.repositories.ButacasRepositoryImpl
import org.example.productos.butaca.services.ButacaService
import org.example.productos.butaca.services.ButacaServiceImpl
import org.example.productos.butaca.storage.ButacaStorage
import org.example.productos.butaca.storage.ButacaStorageImpl
import org.example.productos.butaca.validator.ButacaValidator
import org.example.productos.complementos.cache.ComplementoCacheImpl
import org.example.productos.complementos.repositories.ComplementoRepository
import org.example.productos.complementos.repositories.ComplementoRepositoryImpl
import org.example.productos.complementos.services.ComplementoService
import org.example.productos.complementos.services.ComplementoServiceImpl
import org.example.productos.complementos.storage.ComplementStorageImpl
import org.example.productos.complementos.storage.ComplementoStorage
import org.koin.dsl.module

val butacaModule= module {
    single <ButacasRepository>{ ButacasRepositoryImpl() }
    single <ButacaValidator>{ ButacaValidator() }
    single <ButacasCacheImpl>{ ButacasCacheImpl(5) }
    single <ButacaStorage>{ ButacaStorageImpl(get()) }

    single <ButacaService>{
        ButacaServiceImpl(
            repository = get(),
            validador = get(),
            cache = get(),
            storage = get()
        )
    }
}

val complementoModule = module {
    single <ComplementoRepository>{ ComplementoRepositoryImpl() }
    single <ComplementoCacheImpl>{ ComplementoCacheImpl(5) }
    single <ComplementoStorage>{ ComplementStorageImpl() }

    single <ComplementoService>{
        ComplementoServiceImpl(
            get(),
            get(),
            get()
        )
    }
}