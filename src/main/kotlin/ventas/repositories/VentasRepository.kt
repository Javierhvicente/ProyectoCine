package org.example.ventas.repositories

import org.example.ventas.models.Venta
import java.util.UUID

interface VentasRepository {
    fun getById(id: UUID): Venta?
    fun save(venta: Venta): Venta
    fun getAll(): List<Venta>
    fun update(id: UUID, venta: Venta): Venta?
    fun delete(id: UUID): Venta?
}