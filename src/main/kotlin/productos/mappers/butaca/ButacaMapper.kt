package org.example.productos.mappers.butaca

import database.ButacaEntity
import org.example.productos.models.Butaca
import org.example.productos.models.Estado
import org.example.productos.models.Tipo

fun ButacaEntity.toButaca():Butaca{
    var _id:String=this.id
    var _estado:Estado?=null
    var _tipo:Tipo?=null
    when(this.tipo){
        "Normal" -> _tipo = Tipo.NORMAL
        "VIP"-> _tipo = Tipo.VIP
    }
    when(this.estado){
        "Activa" -> _estado = Estado.ACTIVA
        "Mantenimiento"-> _estado=Estado.MANTENIMIENTO
        "OutServive"-> _estado=Estado.OUTSERVICE
    }
    return Butaca(_id,_estado!!,_tipo!!)

}