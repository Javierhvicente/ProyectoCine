package org.example.ventas.services

import org.example.cliente.models.Cliente
import org.example.ventas.errors.VentaError
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
import java.io.File
import java.util.*
import  com.github.michaelbull.result.Result

interface VentasService {
    fun getById(id: UUID): Result<Venta, VentaError>
    fun create(venta: Venta): Result<Venta, VentaError>
    fun create(cliente: Cliente, lineas: List<LineaVenta>): Result<Venta, VentaError>
    fun exportToHtml(venta: Venta, htmlFile: File): Result<Unit, VentaError>
}