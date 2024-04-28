package org.example.ventas.storage

import com.github.michaelbull.result.Result
import org.example.ventas.errors.VentaError
import org.example.ventas.models.Venta
import java.io.File

interface VentasStorage {
    fun export(venta: Venta, file: File): Result<Unit, VentaError>
}