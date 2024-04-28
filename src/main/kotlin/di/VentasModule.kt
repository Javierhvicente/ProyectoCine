package org.example.di

import org.example.ventas.repositories.VentasRepository
import org.example.ventas.repositories.VentasRepositoryImpl
import org.example.ventas.services.VentasService
import org.example.ventas.services.VentasServiceImpl
import org.example.ventas.storage.VentasStorageHtml
import org.koin.dsl.module

val ventasModule = module {
    single <VentasRepository>{
        VentasRepositoryImpl(
            clienteRepository = get(),
            butacasRepository = get(),
            complementosRepository = get()
        )
    }

    single <VentasStorageHtml>{ VentasStorageHtml() }

    single <VentasService>{
        VentasServiceImpl(
            ventasRepository = get(),
            clienteRepositoryImpl = get(),
            complementoRepository = get(),
            butacasRepository = get(),
            ventasSotrageHtml = get()
        )
    }
}