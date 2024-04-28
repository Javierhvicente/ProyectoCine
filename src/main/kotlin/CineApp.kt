package org.example


import org.example.productos.butaca.services.ButacaService
import org.example.ventas.services.VentasService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CineApp : KoinComponent{

    fun run(){
        val butacasService: ButacaService by inject()
        val complementoService: ButacaService by inject()
        val ventaService : VentasService by inject()

        
    }

}