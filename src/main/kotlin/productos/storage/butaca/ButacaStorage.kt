package org.example.productos.storage.butaca

import org.example.productos.errors.butaca.ButacaError
import org.example.productos.models.Butaca
import java.io.File
import com.github.michaelbull.result.Result

interface ButacaStorage {
    fun save(feha:String, list: List<Butaca>): Result<Unit, ButacaError>
    fun load(file: File): Result<List<Butaca>, ButacaError>
}