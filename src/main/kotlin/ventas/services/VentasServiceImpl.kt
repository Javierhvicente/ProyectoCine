package org.example.ventas.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import org.example.cliente.models.Cliente
import org.example.cliente.repositories.ClienteRepositoryImpl
import org.example.productos.butaca.repositories.ButacasRepository
import org.example.productos.complementos.repositories.ComplementoRepository
import org.example.ventas.errors.VentaError
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
import org.example.ventas.repositories.VentasRepository
import org.example.ventas.storage.VentasStorageHtml
import org.lighthousegames.logging.logging
import java.io.File
import java.util.*

private val logger = logging()
class VentasServiceImpl(
    private val ventasRepository: VentasRepository,
    private val clienteRepositoryImpl: ClienteRepositoryImpl,
    private val complementoRepository: ComplementoRepository,
    private val butacasRepository: ButacasRepository,
    private val ventasSotrageHtml: VentasStorageHtml
): VentasService{
    override fun getById(id: UUID): Result<Venta, VentaError> {
        logger.debug { "Obteniendo venta por id: $id" }
        return ventasRepository.getById(id)
            ?. let { Ok(it) }
            ?: Err(VentaError.VentaNoEncontrada("No se ha encontrado la venta con id: $id"))
    }

    override fun create(venta: Venta): Result<Venta, VentaError> {
        logger.debug { "Creando venta: $venta" }
        return validateCliente(venta.cliente)
            .andThen { validateLineas(venta.lineas) }
            .andThen { Ok(ventasRepository.save(venta)) }
    }
    private fun validateCliente(cliente: Cliente): Result<Cliente, VentaError> {
        logger.debug { "Validando cliente: $cliente" }
        return clienteRepositoryImpl.findById(cliente.id)
            ?.let { Ok(it) }
            ?: Err(VentaError.VentaNoValida("Cliente no encontrado con id: ${cliente.id}"))
    }

    private fun validateLineas(lineas: List<LineaVenta>): Result<List<LineaVenta>, VentaError> {
        logger.debug { "Validando lineas - Existen Productos: $lineas" }
        lineas.forEach {
            butacasRepository.findById(it.butaca.id)
                ?: return Err(VentaError.VentaNoValida("Producto no encontrado con id: ${it.butaca.id}"))
        }
        lineas.forEach {
            it.complemento1?.let { it1 -> butacasRepository.findById(it1.id) }
                ?: return Err(VentaError.VentaNoEncontrada("Complemento no encontrado con id: ${it.complemento1?.id}"))
        }
        lineas.forEach {
            it.complemento3?.let { it1 -> butacasRepository.findById(it1.id) }
                ?: return Err(VentaError.VentaNoEncontrada("Complemento no encontrado con id: ${it.complemento2?.id}"))
        }
        lineas.forEach {
            it.complemento3?.let { it1 -> butacasRepository.findById(it1.id) }
                ?: return Err(VentaError.VentaNoEncontrada("Complemento no encontrado con id: ${it.complemento3?.id}"))
        }

        logger.debug { "Validando lineas - Cantidad y Stock de productos: $lineas" }
        lineas.forEach {
            if (it.cantidad <= 0) {
                return Err(VentaError.VentaNoValida("La cantidad de productos debe ser mayor que 0"))
            }
        }
        return Ok(lineas)
    }


    override fun create(cliente: Cliente, lineas: List<LineaVenta>): Result<Venta, VentaError> {
        logger.debug { "Creando venta con Cliente y lineas: $cliente, $lineas" }
        return validateCliente(cliente)
            .andThen { validateLineas(lineas) }
            .andThen { Ok(ventasRepository.save(Venta(cliente = cliente, lineas = lineas))) }
    }

    override fun exportToHtml(venta: Venta, htmlFile: File): Result<Unit, VentaError> {
        logger.debug { "Exportando venta a ficheo html $htmlFile" }
        return ventasSotrageHtml.export(venta, htmlFile)
    }
}