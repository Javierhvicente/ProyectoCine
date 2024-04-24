package org.example.productos.storage.complemento

import com.github.michaelbull.result.Result
import org.example.productos.errors.ComplementoError
import org.example.productos.errors.butaca.ButacaError
import org.example.productos.models.Butaca
import org.example.productos.models.Complemento
import java.io.File

interface ComplementoStorage {
    fun load(file: File): Result<List<Complemento>, ComplementoError>
}