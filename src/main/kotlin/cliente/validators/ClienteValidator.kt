package org.example.cliente.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.cliente.errors.ClienteErrors
import org.example.cliente.models.Cliente

class ClienteValidator {
    fun validate(cliente: Cliente): Result<Cliente, ClienteErrors>{
        return when{
            cliente.nombre.isBlank() -> Err(ClienteErrors.clienteInvalido("El nombre del personaje no puede estar vacío"))
            else -> Ok(cliente)
        }
    }
}
