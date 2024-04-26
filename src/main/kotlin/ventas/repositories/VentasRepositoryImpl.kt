package org.example.ventas.repositories

import org.example.cliente.repositories.ClienteRepository
import org.example.productos.butaca.repositories.ButacasRepository
import org.example.productos.complementos.repositories.ComplementoRepository
import org.example.ventas.models.Venta
import org.lighthousegames.logging.logging
import java.util.*

private val logger = logging()
class VentasRepositoryImpl(
    private val clienteRepository: ClienteRepository,
    private val butacasRepository: ButacasRepository,
    private val complementosRepository: ComplementoRepository
): VentasRepository {
    override fun getById(id: UUID): Venta? {
        TODO("Not yet implemented")
    }

    override fun save(venta: Venta): Venta {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Venta> {
        TODO("Not yet implemented")
    }

    override fun update(id: UUID, venta: Venta): Venta? {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID): Venta? {
        TODO("Not yet implemented")
    }


}