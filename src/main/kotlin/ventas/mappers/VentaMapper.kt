package org.example.ventas.mappers

import database.LineaVentaEntity
import database.VentaEntity
import org.example.cliente.models.Cliente
import org.example.productos.models.Butaca
import org.example.productos.models.Complemento
import org.example.productos.models.Producto
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
import java.time.LocalDateTime
import java.util.*

fun LineaVentaEntity.toLineaVenta(Butaca: Butaca, complemento1: Complemento?, complemento2: Complemento?, complemento3: Complemento?): LineaVenta {
    return LineaVenta(
        id = UUID.fromString(this.id),
        butaca = Butaca,
        complemento1 = complemento1,
        complemento2 = complemento2,
        complemento3 = complemento3,
        cantidad = this.cantidad.toInt(),
        precio = this.precio,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
    )
}

fun VentaEntity.toVenta(cliente: Cliente, lineas: List<LineaVenta>): Venta {
    return Venta(
        id = UUID.fromString(this.id),
        cliente = cliente,
        lineas = lineas,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
    )
}
