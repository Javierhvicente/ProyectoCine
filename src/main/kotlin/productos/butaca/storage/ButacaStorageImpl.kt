package org.example.productos.butaca.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.productos.butaca.dto.ButacaDto
import org.example.productos.butaca.mappers.toButaca
import org.example.productos.butaca.mappers.toButacaDto
import org.example.productos.errors.butaca.ButacaError
import org.example.productos.models.Butaca
import org.example.productos.butaca.validator.ButacaValidator
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path
import org.lighthousegames.logging.logging

private val logger = logging()
class ButacaStorageImpl(
    private val validator: ButacaValidator
): ButacaStorage {
    override fun save(feha: String, list: List<Butaca>): Result<Unit, ButacaError> {
        if (!validator.validarFecha(feha)) Err(ButacaError.FechaInvalido("La fecha introducida no es valida. Formato AAAA/MM/DD"))
        logger.debug { "Guardando butacas en fichero json" }
        Files.createDirectories(Path("data"))
        val file = Path("data", "butacas-$feha.json").toFile()
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            Ok(file.writeText(json.encodeToString<List<ButacaDto>>(list.map { it.toButacaDto() })))
        }catch (e: Exception){
            logger.error { "Error al guardar el fichero json de butacas" }
            Err(ButacaError.FicheroNoValido("Error al guardar el fichero json"))
        }
    }

    override fun load(file: File): Result<List<Butaca>, ButacaError> {
        logger.debug { "Carganado butacas desde fichero Csv" }
        return try {
            Ok(file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    ButacaDto(
                        id = data[0],
                        estado = data[1],
                        tipo = data[2],
                        precio = data[3],
                        ocupacion = data[4]
                    ).toButaca()
                }
            )
        }catch (e: Exception){
            logger.error { "Error al cargar el fichero csv " }
            Err((ButacaError.FicheroNoValido("Error al leer el fichero csv")))
        }

    }
}