package org.example.productos.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.productos.errors.butaca.ButacaError
import org.example.productos.models.Butaca

class ButacaValidator {

    fun validarButaca(butaca:Butaca) {
        when {
            !validarId(butaca.id) -> Err(ButacaError.IdNoValido("El ID: ${butaca.id} no es valido"))
            else -> Ok(butaca)
        }

    }

    private fun validarId(id: String) :Boolean{
        if (id.length != 2) return false
        val letra=id.slice(0..0)
        val letras= arrayOf("A","B","C","D","E")
        if (letra.uppercase() !in letras) return false
        val numero= id.slice(1..1).toIntOrNull()?: -1
        if (numero == -1)return false
        if (numero !in 1..7) return false
        return true
    }
}