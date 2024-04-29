package org.example.productos.complementos.storage

import com.github.michaelbull.result.Result
import org.example.productos.complementos.errors.ComplementoError
import org.example.productos.models.Complemento
import java.io.File

interface ComplementoStorage {
    fun load(file: File): Result<List<Complemento>, ComplementoError>
}