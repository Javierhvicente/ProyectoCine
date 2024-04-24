package org.example.productos.services.butaca

import org.example.productos.errors.butaca.ButacaError
import org.example.productos.models.Butaca
import com.github.michaelbull.result.Result
import java.io.File

interface ButacaService {
    fun getAll(): Result<List<Butaca>, ButacaError>
    fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError>
    fun getById(id: String): Result<Butaca, ButacaError>
    fun create(butaca: Butaca): Result<Butaca, ButacaError>
    fun update(id: String, butaca: Butaca): Result<Butaca, ButacaError>
    fun delete(id: String): Result<Butaca, ButacaError>
    fun import(csvFile: File): Result<List<Butaca>, ButacaError>
    fun export(fecha:String,list: List<Butaca>): Result<Unit, ButacaError>
}