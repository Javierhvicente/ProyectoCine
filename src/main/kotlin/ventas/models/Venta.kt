package org.example.ventas.models

import org.example.cliente.models.Cliente
import java.time.LocalDateTime
import java.util.UUID

data class Venta(
    val id: UUID = UUID.randomUUID(),
    val cliente: Cliente,
    val lineas: List<LineaVenta>,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    val total: Double
        get() = lineas.sumOf { it.precio * it.cantidad }

    override fun toString(): String {
        return "Venta(id=$id, cliente=$cliente, lineas=$lineas, createdAt=$createdAt, updated=$updatedAt, total=$total)"
    }
}
