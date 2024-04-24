package org.example.productos.dto.butaca

import kotlinx.serialization.Serializable
import org.example.productos.models.Estado
import org.example.productos.models.Ocupacion
import org.example.productos.models.Tipo

@Serializable
class ButacaDto(
    val id:String,
    val estado: String,
    val tipo: String,
    val precio:String,
    var ocupacion: String
)