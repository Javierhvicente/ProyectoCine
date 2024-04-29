package org.example.productos.butaca.dto

import kotlinx.serialization.Serializable

/**
 * La clase dto de las butacas la vamos a necesitar
 * cuando queramos leer el csv y cuando queramos exportar el csv
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
@Serializable
class ButacaDto(
    val id:String,
    val estado: String,
    val tipo: String,
    val precio:String,
    var ocupacion: String,
    var createAt: String,
)