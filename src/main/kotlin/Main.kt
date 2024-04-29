package org.example

import org.example.cliente.cache.CacheClienteImpl
import org.example.cliente.repositories.ClienteRepositoryImpl
import org.example.cliente.services.ClienteServiceImpl
import org.example.cliente.validators.ClienteValidator
import org.example.config.Config
import org.example.di.butacaModule
import org.example.di.clienteModule
import org.example.di.complementoModule
import org.example.di.ventasModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.fileProperties

@OptIn(KoinExperimentalAPI::class)
fun main() {

    startKoin {
           // declare used logger
           printLogger()
           // Leemos las propiedades de un fichero
           fileProperties("/config.properties") // Por defecto busca en src/main/resources/config.properties, pero puede ser otro fichero si se lo pasas como parametro
           // declara modulos de inyección de dependencias, pero lo verificamos antes de inyectarlos
           // para asegurarnos que todo está correcto y no nos de errores
           /*
                   configModule.verify() // Esta en el test
                   databaseModule.verify(extraTypes = listOf(Boolean::class)) // Esta en el test
                   clientesModule.verify() // Esta en el test
                   productosModule.verify() // Esta en el test
                   ventasModule.verify() // Esta en el test*/
           modules(listOf(clienteModule, butacaModule, complementoModule, ventasModule))
    }

    val app= CineApp()
     app.run()



}