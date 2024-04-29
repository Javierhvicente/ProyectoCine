package org.example.productos.complementos.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.productos.complementos.dto.ComplementoDto
import org.example.productos.complementos.errors.ComplementoError
import org.example.productos.complementos.mappers.toComplemento
import org.example.productos.models.Complemento
import java.io.File
import org.lighthousegames.logging.logging

private val logger = logging()
class ComplementStorageImpl: ComplementoStorage {
    /**
     *leer un fichero csv
     * @param file
     * @author Yahya el hadri el bakkali
     * @since 1.0
     */
    override fun load(file: File): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Carganado complementos desde fichero Csv" }
        return try {
            Ok(file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    ComplementoDto(
                        tipoComplemento = data[0],
                        nombre = data[1],
                        precio = data[2],
                    ).toComplemento()
                }
            )
        }catch (e: Exception){
            logger.error { "Error al cargar el fichero csv " }
            Err((ComplementoError.FicheroNoValido("Error al leer el fichero csv")))
        }
    }
}