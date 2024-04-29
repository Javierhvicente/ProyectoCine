package org.example.ventas.models

import org.example.productos.models.Butaca
import org.example.productos.models.Complemento
import org.example.productos.models.Producto
import java.time.LocalDateTime
import java.util.UUID

data class LineaVenta(
    val id: UUID = UUID.randomUUID(),
    val butaca: Butaca,
    val complemento1: Complemento? = null,
    val complemento2: Complemento? = null,
    val complemento3: Complemento? = null,
    val cantidad: Int,
    val precio: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
